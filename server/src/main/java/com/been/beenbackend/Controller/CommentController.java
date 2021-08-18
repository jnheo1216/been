package com.been.beenbackend.Controller;

import com.been.beenbackend.Service.CommentService;
import com.been.beenbackend.dto.Comment;
import com.been.beenbackend.dto.LikeComment;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="*")
@RestController
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value="comment 전체 리스트 받아오기(read)")
    @GetMapping(value="/comment")
    public ResponseEntity<Map<String, Object>> list() throws Exception {
        List<Comment> comments = commentService.list();
        Map<String, Object> result = new HashMap<>();
        result.put("comments", comments);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="comment 하나만 받아오기(read)")
    @GetMapping(value="/comment/{commentId}")
    public ResponseEntity<Map<String, Object>> list(@PathVariable int commentId) throws Exception {
        Comment comment = commentService.listOne(commentId);
        Map<String, Object> result = new HashMap<>();
        result.put("comment", comment);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="comment postId로 받아오기(read)")
    @GetMapping(value="/comment/listByPost/{postId}")
    public ResponseEntity<Map<String, Object>> listByPostId(@PathVariable int postId) throws Exception {
        List<Comment> comments = commentService.listByPostId(postId);
        Map<String, Object> result = new HashMap<>();
        result.put("comments", comments);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="comment 등록하기(create)")
    @PostMapping(value="/comment")
    public ResponseEntity register(@RequestBody Comment comment) throws Exception {
        int cnt = commentService.register(comment);
        if(cnt != 0) {
            return new ResponseEntity(HttpStatus.OK);
        } else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value="comment 수정하기(update)")
    @PutMapping(value= "/comment")
    public ResponseEntity<Map<String, Object>> modify(@RequestBody Comment comment) throws Exception {
        commentService.modify(comment);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value="comment 삭제하기(delete)")
    @DeleteMapping(value = "/comment/{commentId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int commentId) throws Exception {
        commentService.delete(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value="comment 좋아요 등록(create)")
    @PostMapping(value="/comment/like")
    public ResponseEntity makeLike(@RequestBody LikeComment likeComment) throws Exception {
        int cnt = commentService.makeLike(likeComment.getCommentId(), likeComment.getUserId());
        if(cnt != 0) {
            return new ResponseEntity(HttpStatus.OK);
        } else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation(value="comment 좋아요 삭제하기(delete)")
    @DeleteMapping(value = "/comment/like")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody LikeComment likeComment) throws Exception {
        commentService.deleteLike(likeComment.getCommentId(), likeComment.getUserId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value="comment 좋아요 개수(read)")
    @GetMapping(value="/comment/like/{commentId}")
    public ResponseEntity<Map<String, Integer>> showLikeyCnt(@PathVariable int commentId) throws Exception {
        int likeys = commentService.showLikeyCnt(commentId);
        Map<String, Integer> result = new HashMap<>();
        result.put("likes", likeys);
        return new ResponseEntity<Map<String, Integer>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="comment 게시물당 유저가 좋아요 누른 댓글 리스트(read)")
    @GetMapping(value="/comment/likeListByPost/{postId}/{userId}")
    public ResponseEntity<Map<String, Object>> getLikeyList(@PathVariable int postId, @PathVariable int userId) throws Exception {
        List<LikeComment> likeList = commentService.getLike(postId,userId);
        Map<String, Object> result = new HashMap<>();
        result.put("likeList", likeList);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}
