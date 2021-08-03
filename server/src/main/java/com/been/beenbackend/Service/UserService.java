package com.been.beenbackend.Service;

import com.been.beenbackend.dto.User;
import com.been.beenbackend.dto.follow;

import java.util.List;

public interface UserService {
    List<User> list();

    int register(User user);

    void modify(User user);

    void delete(int id);

    User list(int id);

    User list(String email);

    User signIn(String email, String password);

    void confirmEmail(User user);

    void createFollow(int followedId, int followerId);

    void deleteFollow(int followedId, int followerId);

    void acceptFollow(int followedId, int followerId);

    List<User> findUserByEmail(String email);

    List<User> findUserByNickname(String nickname);

    void updatePic(User user);

    List<User> showFollowing(int id);

    List<User> showFollower(int id);

    List<follow> beforeFollowList(int id);
}
