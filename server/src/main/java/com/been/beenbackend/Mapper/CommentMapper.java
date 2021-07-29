package com.been.beenbackend.Mapper;

import com.been.beenbackend.dto.Comment;

import java.util.List;

public interface CommentMapper {
    List<Comment> list();

    int register(Comment comment);

    void modify(Comment comment);

    void delete(String commentId);

    Comment listOne(String commentId);
}
