package com.been.beenbackend.Controller;

import com.been.beenbackend.Service.*;
import com.been.beenbackend.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="*")
@RestController
@Slf4j

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private EmailConfirmationService emailConfirmationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value="user 로그인")
    @PostMapping("/user/signin")
    public ResponseEntity<Map<String, Object>> signIn(@RequestBody User user, HttpServletResponse res) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            User loginUser = userService.signIn(user.getEmail(), user.getPassword());
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            if(!passwordEncoder.matches(user.getPassword(),loginUser.getPassword())) {
                System.out.println("비밀번호 불일치");
                loginUser = null;
            } else {
                System.out.println("비밀번호 일치");
                // 로그인 성공했다면 토큰을 생성한다.
                String token = jwtService.create(loginUser);
                // 토큰 정보는 request의 헤더로 보내고 나머지는 Map에 담아주자.
                res.setHeader("jwt-auth-token", token);
                // resultMap.put("auth_token", token);
            }
            resultMap.put("status", true);
            resultMap.put("data", loginUser);
            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
            log.error("로그인 실패", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value="user 회원가입(create)") //415 Unsupproted Media Type 에러 발생. 아마도 따로 넣어줘야 하는듯? https://galid1.tistory.com/754 참고
    @PostMapping(value="/user")
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody User user) throws Exception {

        //이메일 중복 조회
        User exist = userService.list(user.getEmail());
        if(exist != null) {
            return new ResponseEntity<Map<String,Object>>(HttpStatus.valueOf("이메일 중복"));
        }

        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        //회원 db에 집어넣기
        int id = userService.register(user);
//        int id = userService.list(user.getEmail()).getId();
        if(id >= 0) {
            //인증 이메일 보내기
            emailConfirmationService.createEmailConfirmation(id,user.getEmail());

            //잘 들어갔는지 확인용
            List<User> users =userService.list();
            Map<String, Object> result = new HashMap<>();
            result.put("users", users);
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value="user 관심지역 넣기(create)")
    @PostMapping(value="/user/preferredArea")
    public ResponseEntity<Map<String, Object>> makePreferredArea(@RequestBody PreferredArea preferredArea) throws Exception {
        userService.makePreferredArea(preferredArea);
        List<String> areas = userService.getPreferedArea(preferredArea.getUserId());
        Map<String, Object> result = new HashMap<>();
        result.put("areas", areas);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user 관심스타일 넣기(create)")
    @PostMapping(value="/user/preferredStyle")
    public ResponseEntity<Map<String, Object>> makePreferredStyle(@RequestBody PreferredStyle preferredStyle) throws Exception {
        userService.makePreferredStyle(preferredStyle);
        List<String> styles = userService.getPreferedStyle(preferredStyle.getUserId());
        Map<String, Object> result = new HashMap<>();
        result.put("styles", styles);
        return new ResponseEntity<Map<String, Object>>(result,HttpStatus.OK);
    }

    @ApiOperation(value="user 관심지역 받기(create)")
    @GetMapping(value="/user/preferredArea/{userId}")
    public ResponseEntity<Map<String, Object>> getPreferredArea(@PathVariable int userId) throws Exception {
        List<PreferredArea> areas = userService.getPreferedAreaAll(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("areas", areas);
        return new ResponseEntity<Map<String, Object>>(result,HttpStatus.OK);
    }

    @ApiOperation(value="user 관심스타일 받기(create)")
    @GetMapping(value="/user/preferredStyle/{userId}")
    public ResponseEntity<Map<String, Object>> getPreferredStyle(@PathVariable int userId) throws Exception {
        List<String> styles = userService.getPreferedStyle(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("areas", styles);
        return new ResponseEntity<Map<String, Object>>(result,HttpStatus.OK);
    }

    @ApiOperation(value="user 관심지역 삭제(delete)")
    @PostMapping(value="/user/deletePreferredArea")
    public ResponseEntity<Map<String, Object>> deletePreferredArea(@RequestBody PreferredArea preferredArea) throws Exception {
        userService.deletePreferedArea(preferredArea);
        return getPreferredArea(preferredArea.getUserId());
    }

    @ApiOperation(value="user 관심스타일 삭제(delete)")
    @PostMapping(value="/user/deletePreferredStyle")
    public ResponseEntity<Map<String, Object>> deletePreferredStyle(@RequestBody PreferredStyle preferredStyle) throws Exception {
        userService.deletePreferedStyle(preferredStyle);
        return getPreferredStyle(preferredStyle.getUserId());
    }

    @ApiOperation(value="user 프로필 사진 넣기(update)")
    @PutMapping(value= "/user/profilePic/{email}")
    public ResponseEntity<Map<String, Object>> modify(@RequestPart MultipartFile file,@PathVariable String email) throws Exception {
        //회원 프로필 이미지 넣기
        String fileName = "userProfile_"+email+".jpg";
        String imgPath = s3Service.uploadObject(file,fileName);
        User user = new User();
        user.setProfilePicSrc(imgPath);
//        user.setProfilePicName(file.getOriginalFilename());
        user.setProfilePicName(fileName);
        user.setEmail(email);
        userService.updatePic(user);
        return list();
    }

    @ApiOperation(value="user 이메일 인증(update)")
    @GetMapping(value="/user/confirmEmail")
    public void confirmEmail(@RequestParam("id") int id, HttpServletResponse response) throws Exception {
        User user = userService.list(id);
//        if(user == null) return "만료된 요청입니다";
//        if(user.getEmailConfirmation() == 1) return "이미 인증된 회원입니다";
        userService.confirmEmail(user);
        String redirect_uri="http://i5b301.p.ssafy.io/";
        response.sendRedirect(redirect_uri);
//        return "유저 인증 완료. 홈페이지에서 로그인해주세요";
    }

    @ApiOperation(value="user 임시비밀번호 발급 후 이메일 보내기 (update)")
    @PutMapping(value="/user/temporaryPassword/{email}")
    public String temporaryPassword(@PathVariable String email) throws Exception {
        User user = userService.list(email);
        if(user == null) return "유저를 찾을 수 없습니다";

        //임시 비밀번호 생성
        String ENGLISH_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String ENGLISH_UPPER = ENGLISH_LOWER.toUpperCase();
        String NUMBER = "0123456789";
        String data = ENGLISH_LOWER+ENGLISH_UPPER+NUMBER;
        String temporaryPassword = "";
        for(int i = 0; i < 10; i++) {
            temporaryPassword += data.charAt((int)(Math.random()*data.length()));
        }

        //임시 비밀번호 이메일로 보내기
        emailConfirmationService.createTemporaryPasswordEmail(email, temporaryPassword);

        //임시 비밀번호 저장
        String encodedPassword = passwordEncoder.encode(temporaryPassword);
        user.setPassword(encodedPassword);
        userService.modify(user);
        return "이메일을 확인해주세요";
    }

    @ApiOperation(value="user 회원탈퇴(delete)")
    @DeleteMapping(value = "/user/withdrwal/{id}")
    public ResponseEntity<Map<String, Object>> withdrawal(@PathVariable int id) throws Exception {
        User user = userService.list(id);
        String fileName = user.getProfilePicName();
        //프로필 사진 삭제
        if(!fileName.equals("defaultProfile.png")) {
            s3Service.deleteObject(fileName);
        }
        //포스트 사진 삭제
        List<Post> posts = new ArrayList<>();
        int cnt = 1;
        List<Post> pagePost = postService.listByUser(user.getId(),cnt++);
        posts.addAll(pagePost);
        while(pagePost.size() != 0) {
            pagePost = postService.listByUser(user.getId(),cnt++);
            posts.addAll(pagePost);
            System.out.println("포스트 찾는중...");
        }
        for( Post post : posts) {
            if(post.getPostPicName() != "꿀벌썸네일.png") {
                List<PostPic> postPics = postService.getPostPic(post.getPostId());
                for(int i = 0; i < postPics.size(); i++) {
                s3Service.deleteObject(postPics.get(i).getName());
                }
            }
        }

        //유저 db에서 삭제. cacscde로 fk로 연결된 것 전부다 삭제
        userService.delete(user.getId());
        return list();
    }

    @ApiOperation(value="user email로 찾기(read)")
    @GetMapping(value="/user/findEmail/{email}/{page}")
    public ResponseEntity<Map<String, Object>> findUserByEmail(@PathVariable String email, @PathVariable int page) throws Exception {
        List<User> users = userService.findUserByEmail(email, page);
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user nickname으로 찾기(read)")
    @GetMapping(value="/user/findNickname/{nickname}/{page}")
    public ResponseEntity<Map<String, Object>> findUserByNickname(@PathVariable String nickname, @PathVariable int page) throws Exception {
        List<User> users = userService.findUserByNickname(nickname, page);
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user 팔로워 표시(read)")
    @GetMapping(value="/user/showFollower/{id}/{page}")
    public ResponseEntity<Map<String, Object>> showFollower(@PathVariable int id, @PathVariable int page) throws Exception {
        List<User> users = userService.showFollower(id, page);
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user가 팔로우하는 유저 표시(read)")
    @GetMapping(value="/user/showFollowing/{id}/{page}")
    public ResponseEntity<Map<String, Object>> showFollowing(@PathVariable int id, @PathVariable int page) throws Exception {
        List<User> users = userService.showFollowing(id, page);
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user가 팔로우하는 유저 명수 표시(read)")
    @GetMapping(value="/user/showFollowingCnt/{followingId}")
    public ResponseEntity<Map<String, Integer>> showFollowingCnt(@PathVariable int followingId) throws Exception {
        int followingCnt = userService.showFollowingCnt(followingId);
        Map<String, Integer> result = new HashMap<>();
        result.put("followingCnt", followingCnt);
        return new ResponseEntity<Map<String, Integer>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user를 팔로우하는 유저 명수 표시(read)")
    @GetMapping(value="/user/showFollowerCnt/{followerId}")
    public ResponseEntity<Map<String, Integer>> showFollowerCnt(@PathVariable int followerId) throws Exception {
        int followerCnt = userService.showFollowerCnt(followerId);
        Map<String, Integer> result = new HashMap<>();
        result.put("followerCnt", followerCnt);
        return new ResponseEntity<Map<String, Integer>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user 리스트 받아오기(read)")
    @GetMapping(value="/user")
    public ResponseEntity<Map<String, Object>> list() throws Exception {
        List<User> users = userService.list();
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user 하나만 받아오기(read)")
    @GetMapping(value="/user/{id}")
    public ResponseEntity<Map<String, Object>> list(int id) throws Exception {
        User user = userService.list(id);
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user 등록하기(create)")
    @PostMapping(value="/user/create")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) throws Exception {
        int cnt = userService.register(user);
        if(cnt != 0) {
            List<User> users =userService.list();
            Map<String, Object> result = new HashMap<>();
            result.put("users", users);
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
        } else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value="user 수정하기(update)")
    @PutMapping(value= "/user")
    public ResponseEntity<Map<String, Object>> modify(@RequestBody User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.modify(user);
        return list();
    }

    @ApiOperation(value="user 삭제하기(delete)")
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int id) throws Exception {
        userService.delete(id);
        return list();
    }

    @ApiOperation(value="user 팔로우(create)")
    @PostMapping(value = "/user/{followedId}/{followerId}")
    public void createFollow(@PathVariable("followedId") int followedId, @PathVariable("followerId") int followerId ) throws Exception {
        userService.createFollow(followedId, followerId);
    }

    @ApiOperation(value="user 언팔로우(delete)")
    @DeleteMapping(value = "/user/{followedId}/{followerId}")
    public void deleteFollow(@PathVariable("followedId") int followedId, @PathVariable("followerId") int followerId) throws Exception {
        userService.deleteFollow(followedId, followerId);
    }

    @ApiOperation(value="user 팔로우 수락(update)")
    @PutMapping(value = "/user/{followedId}/{followerId}")
    public void acceptFollow(@PathVariable("followedId") int followedId, @PathVariable("followerId") int followerId) throws Exception {
        userService.acceptFollow(followedId, followerId);
    }

    @ApiOperation(value="user 팔로우 대기 리스트(read)")
    @PutMapping(value = "/user/beforeFollowList/{id}/{page}")
    public ResponseEntity<Map<String,Object>> beforeFollowList(@PathVariable("id") int id, @PathVariable int page) throws Exception {
        List<follow> beforeFollowList = userService.beforeFollowList(id, page);
        Map<String,Object> result = new HashMap<>();
        result.put("beforeFollowList",beforeFollowList);
        return new ResponseEntity<Map<String,Object>>(result,HttpStatus.OK);
    }


}
