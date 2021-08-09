package com.been.beenbackend.Controller;

import com.been.beenbackend.Service.*;
import com.been.beenbackend.dto.Post;
import com.been.beenbackend.dto.PostPic;
import com.been.beenbackend.dto.User;
import com.been.beenbackend.dto.follow;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    @ApiOperation(value="user 로그인")
    @PostMapping("/user/signin")
    public ResponseEntity<Map<String, Object>> signIn(@RequestBody User user, HttpServletResponse res) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            User loginUser = userService.signIn(user.getEmail(), user.getPassword());
            // 로그인 성공했다면 토큰을 생성한다.
            String token = jwtService.create(loginUser);
            // 토큰 정보는 request의 헤더로 보내고 나머지는 Map에 담아주자.
            res.setHeader("jwt-auth-token", token);
            // resultMap.put("auth_token", token);

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

    @ApiOperation(value="user 프로필 사진 넣기(update)")
    @PutMapping(value= "/user/profilePic/{email}")
    public ResponseEntity<Map<String, Object>> modify(@RequestPart MultipartFile file,@PathVariable String email) throws Exception {
        //회원 프로필 이미지 넣기
        String fileName = "userProfile_"+email;
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
    public void confirmEmail(@RequestParam("id") int id) throws Exception {
        User user = userService.list(id);
        userService.confirmEmail(user);
    }

    @ApiOperation(value="user 회원탈퇴(delete)")
    @DeleteMapping(value = "/user")
    public ResponseEntity<Map<String, Object>> withdrawal(@RequestBody User user) throws Exception {
        String fileName = user.getProfilePicName();
        //프로필 사진 삭제
        if(!fileName.equals("defaultProfile.png")) {
            s3Service.deleteObject(fileName);
        }
        //포스트 사진 삭제
        List<Post> posts = postService.listByUser(user.getId());
        for( Post post : posts) {
            List<PostPic> postPics = postService.getPostPic(post.getPostId());
            for(int i = 0; i < postPics.size(); i++) {
                s3Service.deleteObject(postPics.get(i).getName());
            }
        }

        //유저 db에서 삭제. cacscde로 fk로 연결된 것 전부다 삭제
        userService.delete(user.getId());
        return list();
    }

    @ApiOperation(value="user email로 찾기(read)")
    @GetMapping(value="/user/findEmail/{email}")
    public ResponseEntity<Map<String, Object>> findUserByEmail(@PathVariable String email) throws Exception {
        List<User> users = userService.findUserByEmail(email);
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user nickname으로 찾기(read)")
    @GetMapping(value="/user/findNickname/{nickname}")
    public ResponseEntity<Map<String, Object>> findUserByNickname(@PathVariable String nickname) throws Exception {
        List<User> users = userService.findUserByNickname(nickname);
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user 팔로워 표시(read)")
    @GetMapping(value="/user/showFollower/{id}")
    public ResponseEntity<Map<String, Object>> showFollower(@PathVariable int id) throws Exception {
        List<User> users = userService.showFollower(id);
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="user가 팔로우하는 유저 표시(read)")
    @GetMapping(value="/user/showFollowing/{id}")
    public ResponseEntity<Map<String, Object>> showFollowing(@PathVariable int id) throws Exception {
        List<User> users = userService.showFollowing(id);
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
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
    @PutMapping(value = "/user/beforeFollowList/{id}")
    public ResponseEntity<Map<String,Object>> beforeFollowList(@PathVariable("id") int id) throws Exception {
        List<follow> beforeFollowList = userService.beforeFollowList(id);
        Map<String,Object> result = new HashMap<>();
        result.put("beforeFollowList",beforeFollowList);
        return new ResponseEntity<Map<String,Object>>(result,HttpStatus.OK);
    }
}
