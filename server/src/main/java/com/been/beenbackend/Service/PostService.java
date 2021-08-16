package com.been.beenbackend.Service;

import com.been.beenbackend.dto.Like;
import com.been.beenbackend.dto.Post;
import com.been.beenbackend.dto.PostPic;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<Post> list();

    List<Post> listByUser(int userId, int page);

    int register(Post post);

    void modify(Post post);

    void delete(int postId);

    Post listOne(int postId);

    List<Post> showFollowPost(int id);

    int makeLike(int postId, int userId);

    void removeLke(int postId, int userId);

    List<Post> likePost(int userId, int page);

    int registerPic(PostPic postPic);

    List<PostPic> getPostPic(int postId);

    void deletePic(int postId);

    int showLikeyCnt(int postId);

    Like getLikey(int postId, int userId);

    List<Post> searchByTitle(Map<String,Object> map);

    List<Post> showPreferedAreaPost(List<String> areas, int page);

    List<Post> showPreferedStylePost(List<String> styles, int page);
}
