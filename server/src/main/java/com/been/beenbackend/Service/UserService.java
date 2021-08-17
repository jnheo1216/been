package com.been.beenbackend.Service;

import com.been.beenbackend.dto.PreferredArea;
import com.been.beenbackend.dto.PreferredStyle;
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

    List<User> findUserByEmail(String email, int page);

    List<User> findUserByNickname(String nickname, int page);

    void updatePic(User user);

    List<User> showFollowing(int id, int page);

    List<User> showFollower(int id, int page);

    List<follow> beforeFollowList(int id, int page);

    int showFollowingCnt(int followerId);

    int showFollowerCnt(int followedId);

    List<String> getPreferedArea(int userId);

    List<String> getPreferedStyle(int userId);

    void makePreferredArea(PreferredArea preferredArea);

    void makePreferredStyle(PreferredStyle preferredStyle);

    List<PreferredArea> getPreferedAreaAll(int userId);

    void deletePreferedArea(PreferredArea preferredArea);

    void deletePreferedStyle(PreferredStyle preferredStyle);
}
