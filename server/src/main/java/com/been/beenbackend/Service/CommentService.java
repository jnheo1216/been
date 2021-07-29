package com.been.beenbackend.Service;

import com.been.beenbackend.dto.Comment;
import com.been.beenbackend.dto.Post;

import java.util.List;

public interface CommentService {
    List<Comment> list();

    int register(Comment comment);

    void modify(Comment comment);

    void delete(String commentId);

    Comment listOne(String commentId);
}
