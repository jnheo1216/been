package com.been.beenbackend.Mapper;

import com.been.beenbackend.dto.PreferredArea;
import com.been.beenbackend.dto.PreferredStyle;
import com.been.beenbackend.dto.User;
import com.been.beenbackend.dto.follow;

import java.util.List;
import java.util.Map;

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

    List<User> findUserByEmail(Map<String,Object> map);

    List<User> findUserByNickname(Map<String,Object> map);

    void updatePic(User user);

    List<User> showFollowing(int id, int start);

    List<User> showFollower(int id, int start);

    List<follow> beforeFollowList(int id, int start);

    int showFollowerCnt(int followerId);

    int showFollowedCnt(int followedId);

    List<String> getPreferedArea(int userId);

    List<String> getPreferedStyle(int userId);

    void makePreferredArea(PreferredArea preferredArea);

    void makePreferredStyle(PreferredStyle preferredStyle);
}

