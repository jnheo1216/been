package com.been.beenbackend.Controller;

import com.been.beenbackend.Service.PostService;
import com.been.beenbackend.Service.S3Service;
import com.been.beenbackend.Service.UserService;
import com.been.beenbackend.dto.Like;
import com.been.beenbackend.dto.Post;
import com.been.beenbackend.dto.PostPic;
import com.been.beenbackend.dto.User;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@Slf4j
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private S3Service s3Service;
    @Autowired
    private UserService userService;

    @ApiOperation(value="post 전체 리스트 받아오기(read)")
    @GetMapping(value="/post")
    public ResponseEntity<Map<String, Object>> list() throws Exception {
        List<Post> posts = postService.list();
//        List<PostPic> postPics = new ArrayList<>();
//        for(int i = 0; i < posts.size(); i++) {
//            int postId = posts.get(i).getPostId();
//            PostPic postPic = postService.getPostPic(postId).get(0);
//            postPics.add(postPic);
//        }
        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);
//        result.put("postPics", postPics);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="post 유저아이디로 받아오기(read)")
    @GetMapping(value="/post/userId/{userid}/{page}")
    public ResponseEntity<Map<String, Object>> listByUser(@PathVariable int userid, @PathVariable int page) throws Exception {
        List<Post> posts = postService.listByUser(userid,page);
        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="post 하나만 받아오기. 디테일 페이지에 사용(read)")
    @GetMapping(value="/post/{postId}")
    public ResponseEntity<Map<String, Object>> getPost(@PathVariable int postId) throws Exception {
        Post post = postService.listOne(postId);
        List<PostPic> postPics = postService.getPostPic(postId);
        Map<String, Object> result = new HashMap<>();
        result.put("post", post);
        result.put("postPics", postPics);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }


    @ApiOperation(value="post 등록하기(create)")
    @PostMapping(value="/post")
    public ResponseEntity<Map<String, Integer>> register(@RequestBody Post post) throws Exception {
        int postId = postService.register(post);
        Map<String, Integer> result = new HashMap<>();
        result.put("postId", postId);
        if(postId >= 0) {
            return new ResponseEntity(result, HttpStatus.OK);
        } else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value="post 등록, 수정 후 사진 넣기(update), *thumbnail 필수! thumbnail이 없으면 db에 있는 기본값으로 만들어주세요! 사진 용량 제한도 있습니다")
    @PostMapping(value= "/post/postPic/{postId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Map<String, Object>> modify(@RequestPart List<MultipartFile> files, @RequestPart MultipartFile thumbnail, @PathVariable int postId) throws Exception {
        //썸네일 사진 넣기
        String fileName = "postThumbnail_"+postId+".jpg";
        String imgPath = s3Service.uploadObject(thumbnail,fileName);
        PostPic postPic = new PostPic();
        postPic.setSrc(imgPath);
//        postPic.setName(thumbnail.getOriginalFilename());
        postPic.setName(fileName);
        postPic.setPostId(postId);
        postPic.setNum(0);
        postService.registerPic(postPic);
        Post post = postService.listOne(postId);
        post.setPostPicName(postPic.getName());
        post.setPostPicSrc(imgPath);
        postService.modify(post);
        //각각의 사진 넣기
        for(int i = 0; i < files.size(); i++){
            fileName = "postPic_"+postId+Integer.toString(i+1)+".jpg";
            imgPath = s3Service.uploadObject(files.get(i),fileName);
            postPic = new PostPic();
            postPic.setSrc(imgPath);
//            postPic.setName(files.get(i).getOriginalFilename());
            postPic.setName(fileName);
            postPic.setPostId(postId);
            postPic.setNum(i+1);
            postService.registerPic(postPic);
//            if(i == 0) { //썸네일 사진 post에 넣어주기
//                Post post = postService.listOne(postId);
//                post.setPostPicName(postPic.getName());
//                post.setPostPicSrc(imgPath);
//                postService.modify(post);
//            }
        }
        return list();
    }

    @ApiOperation(value="post 수정하기(update) *수정하기도 호출 후 post 사진 넣기(update)로 사진을 다시 넣어줘야합니다")
    @PutMapping(value= "/post")
    public ResponseEntity<Map<String, Object>> modify(@RequestBody Post post) throws Exception {
        post.setPostPicSrc("https://been.s3.ap-northeast-2.amazonaws.com/%EA%BF%80%EB%B2%8C%EC%8D%B8%EB%84%A4%EC%9D%BC.png");
        post.setPostPicName("꿀벌썸네일.png");
        postService.modify(post);
        List<PostPic> postPics = postService.getPostPic(post.getPostId());
        for(int i = 0; i < postPics.size(); i++) {
            s3Service.deleteObject(postPics.get(i).getName());
        }
        postService.deletePic(post.getPostId());
        return list();
    }

    @ApiOperation(value="post 삭제하기(delete)") // 수정 요망
    @DeleteMapping(value = "/post/{postId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int postId) throws Exception {
        Post post = postService.listOne(postId);
        if(post.getPostPicName() != "꿀벌썸네일.png") {
            List<PostPic> postPics = postService.getPostPic(postId);
            for(int i = 0; i < postPics.size(); i++) {
                s3Service.deleteObject(postPics.get(i).getName());
            }
        }
        postService.delete(postId);
//        postService.deletePic(postId);
        return list();
    }

    @ApiOperation(value="팔로우한 유저의 post 리스트 받아오기(read)")
    @GetMapping(value="/post/followPost/{userId}")
    public ResponseEntity<Map<String, Object>> showFollowPost(@PathVariable int userId) throws Exception {
        List<Post> posts = postService.showFollowPost(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="post 좋아요 등록(create)")
    @PostMapping(value="/post/like")
    public ResponseEntity makeLike(@RequestBody Like like) throws Exception {
        int cnt = postService.makeLike(like.getPostId(), like.getUserId());
        if(cnt != 0) {
            return new ResponseEntity(HttpStatus.OK);
        } else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value="post 좋아요 삭제하기(delete)")
    @DeleteMapping(value = "/post/like/delete/{postId}/{userId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int postId, @PathVariable int userId) throws Exception {
        System.out.println(postId);
        System.out.println(userId);
        postService.removeLke(postId, userId);
        return list();
    }

    @ApiOperation(value="post 좋아요 개수(read)")
    @GetMapping(value="/post/likeCnt/{postId}")
    public ResponseEntity<Map<String, Integer>> showLikeyCnt(@PathVariable int postId) throws Exception {
        int likeys = postService.showLikeyCnt(postId);
        Map<String, Integer> result = new HashMap<>();
        result.put("likes", likeys);
        return new ResponseEntity<Map<String, Integer>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="post에 유저별 좋아요 여부(read)")
    @GetMapping(value="/post/like/{postId}/{userId}")
    public ResponseEntity<Map<String, Object>> getLikey(@PathVariable int postId, int userId) throws Exception {
        Like like = postService.getLikey(postId, userId);
        Map<String, Object> result = new HashMap<>();
        result.put("like", like);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="좋아요를 누른 post 리스트 받아오기(read)")
    @GetMapping(value="/post/getLikePost/{userId}/{page}")
    public ResponseEntity<Map<String, Object>> showLikePost(@PathVariable int userId, @PathVariable int page) throws Exception {
        List<Post> posts = postService.likePost(userId, page);
        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="post 제목으로 검색")
    @GetMapping(value = "/post/searchByTitle/{title}/{page}")
    public ResponseEntity<Map<String,Object>> searchByTitle(@PathVariable String title, @PathVariable int page) throws Exception {
        title = "%"+title+"%";
        int start = 18 * (page-1);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("title",title);
        map.put("start",start);
        List<Post> posts = postService.searchByTitle(map);
        Map<String,Object> result = new HashMap<>();
        result.put("posts",posts);
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="post 선호지역으로 가져오기")
    @GetMapping(value = "/post/preferedArea/{userId}/{page}")
    public ResponseEntity<Map<String,Object>> showPreferedAreaPost(@PathVariable int userId, @PathVariable int page) throws Exception {
        List<String> areas = userService.getPreferedArea(userId);

        List<Post> posts = postService.showPreferedAreaPost(areas, page);
        Map<String,Object> result = new HashMap<>();
        result.put("posts", posts);
        return new ResponseEntity<Map<String,Object>>(result,HttpStatus.OK);
    }

    @ApiOperation(value="post 선호스타일로 가져오기")
    @GetMapping(value = "/post/preferedStyle/{userId}/{page}")
    public ResponseEntity<Map<String,Object>> showPreferedStylePost(@PathVariable int userId, @PathVariable int page) throws Exception {
        List<String> styles = userService.getPreferedStyle(userId);

        List<Post> posts = postService.showPreferedStylePost(styles, page);
        Map<String,Object> result = new HashMap<>();
        result.put("posts", posts);
        return new ResponseEntity<Map<String,Object>>(result,HttpStatus.OK);
    }
}
