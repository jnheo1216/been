package com.been.beenbackend.Controller;

import com.been.beenbackend.Service.PostService;
import com.been.beenbackend.Service.S3Service;
import com.been.beenbackend.dto.Like;
import com.been.beenbackend.dto.Post;
import com.been.beenbackend.dto.PostPic;
import com.been.beenbackend.dto.User;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="*")
@RestController
@Slf4j
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private S3Service s3Service;

    @ApiOperation(value="post 리스트 받아오기(read)")
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

    @ApiOperation(value="post 하나만 받아오기(read)")
    @GetMapping(value="/post/{postId}")
    public ResponseEntity<Map<String, Object>> getPost(int postId) throws Exception {
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

    @ApiOperation(value="post 사진 넣기(update), 가장 첫번째에 썸네일 이미지를 넣어주세요")
    @PostMapping(value= "/post/postPic/{postId}") //postId를 어떻게 넣는지 생각해야... 여기부터 30일에 하자!
    public ResponseEntity<Map<String, Object>> modify(@RequestPart List<MultipartFile> files, @PathVariable int postId) throws Exception {
        for(int i = 0; i < files.size(); i++){
            String imgPath = s3Service.uploadObject(files.get(i));
            PostPic postPic = new PostPic();
            postPic.setSrc(imgPath);
            postPic.setName(files.get(i).getOriginalFilename());
            postPic.setPostId(postId);
            postPic.setNum(i);
            postService.registerPic(postPic);
            if(i == 0) { //썸네일 사진 post에 넣어주기
                Post post = postService.listOne(postId);
                post.setPostPicName(postPic.getName());
                post.setPostPicSrc(imgPath);
                postService.modify(post);
            }
        }
        return list();
    }

    @ApiOperation(value="post 수정하기(update)") // 수정 요망
    @PutMapping(value= "/post")
    public ResponseEntity<Map<String, Object>> modify(@RequestBody Post post) throws Exception {
        postService.modify(post);
        return list();
    }

    @ApiOperation(value="post 삭제하기(delete)") // 수정 요망
    @DeleteMapping(value = "/post/{postId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int postId) throws Exception {
        postService.delete(postId);
        return list();
    }

    @ApiOperation(value="팔로우한 유저의 post 리스트 받아오기(read)")
    @GetMapping(value="/post/follow/{userId}")
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
    @DeleteMapping(value = "/post/like")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Like like) throws Exception {
        postService.removeLke(like.getPostId(), like.getUserId());
        return list();
    }

    @ApiOperation(value="좋아요를 누른 post 리스트 받아오기(read)")
    @GetMapping(value="/post/follow/{userId}")
    public ResponseEntity<Map<String, Object>> showLikePost(@PathVariable int userId) throws Exception {
        List<Post> posts = postService.likePost(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}
