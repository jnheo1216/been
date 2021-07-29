package com.been.beenbackend.Controller;

import com.been.beenbackend.Service.CommentService;
import com.been.beenbackend.dto.Comment;
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

    @ApiOperation(value="comment 리스트 받아오기(read)")
    @GetMapping(value="/comment")
    public ResponseEntity<Map<String, Object>> list() throws Exception {
        List<Comment> comments = commentService.list();
        Map<String, Object> result = new HashMap<>();
        result.put("comments", comments);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @ApiOperation(value="comment 리스트 하나만 받아오기(read)")
    @GetMapping(value="/comment/{commentId}")
    public ResponseEntity<Map<String, Object>> list(String commentId) throws Exception {
        Comment comment = commentService.listOne(commentId);
        Map<String, Object> result = new HashMap<>();
        result.put("comment", comment);
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
        return list();
    }

    @ApiOperation(value="comment 삭제하기(delete)")
    @DeleteMapping(value = "/comment/{commentId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String commentId) throws Exception {
        commentService.delete(commentId);
        return list();
    }
}
