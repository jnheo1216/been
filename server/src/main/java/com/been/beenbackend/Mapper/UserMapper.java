package com.been.beenbackend.Mapper;

import com.been.beenbackend.dto.User;
import com.been.beenbackend.dto.follow;

import java.util.List;

public interface UserMapper {
    List<User> list();

    int register(User user);

    void modify(User user);

    void delete(int id);

    User listOneById(int id);

    User listOneByEmail(String email);

    User signIn(String email, String password);

    void makeFollow(int followedId, int followerId);

    void removeFollow(int followedId, int followerId);

    void acceptFollow(int followedId, int followerId);

    List<User> findUserByEmail(String email);

    List<User> findUserByNickname(String nickname);

    void updatePic(User user);

    List<User> showFollowing(int id);

    List<User> showFollower(int id);

    List<follow> beforeFollowList(int id);
}

