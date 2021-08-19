package com.been.beenbackend.Service;

import com.been.beenbackend.Mapper.UserMapper;
import com.been.beenbackend.dto.PreferredArea;
import com.been.beenbackend.dto.PreferredStyle;
import com.been.beenbackend.dto.User;
import com.been.beenbackend.dto.follow;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<User> list() {
        return sqlSession.getMapper(UserMapper.class).list();
    }

    @Override
    public int register(User user) {
        sqlSession.getMapper(UserMapper.class).register(user);
        return user.getId();
    }

    @Override
    public void modify(User user) {
        sqlSession.getMapper(UserMapper.class).modify(user);
    }

    @Override
    public void delete(int id) {
        sqlSession.getMapper(UserMapper.class).delete(id);
    }

    @Override
    public User list(int id) {
        return sqlSession.getMapper(UserMapper.class).listOneById(id);
    }

    @Override
    public User list(String email) {
        return sqlSession.getMapper(UserMapper.class).listOneByEmail(email);
    }

    @Override
    public User signIn(String email, String password) {
        return sqlSession.getMapper(UserMapper.class).signIn(email, password);
    }

    @Override
    public void confirmEmail(User user) {
        user.setEmailConfirmation(1);
        sqlSession.getMapper(UserMapper.class).modify(user);
    }

    @Override
    public void createFollow(int followedId, int followerId) {
        sqlSession.getMapper(UserMapper.class).makeFollow(followedId, followerId);
    }

    @Override
    public void deleteFollow(int followedId, int followerId) {
        sqlSession.getMapper(UserMapper.class).removeFollow(followedId, followerId);
    }

    @Override
    public void acceptFollow(int followedId, int followerId) {
        sqlSession.getMapper(UserMapper.class).acceptFollow(followedId, followerId);
    }

    @Override
    public List<User> findUserByEmail(String email, int page) {
        email = "%"+email+"%";
        int start = (page-1) * 18;
        Map<String, Object> map = new HashMap<>();
        map.put("email",email);
        map.put("start",start);
        System.out.println(email);
        return sqlSession.getMapper(UserMapper.class).findUserByEmail(map);
    }

    @Override
    public List<User> findUserByNickname(String nickname, int page) {
        nickname = "%"+nickname+"%";
        int start = (page-1) * 18;
        Map<String, Object> map = new HashMap<>();
        map.put("nickname",nickname);
        map.put("start",start);
        System.out.println(nickname);
        return sqlSession.getMapper(UserMapper.class).findUserByNickname(map);
    }

    @Override
    public void updatePic(User user) {
        sqlSession.getMapper(UserMapper.class).updatePic(user);
    }

    @Override
    public List<User> showFollowing(int id, int page) {
        int start = 18 * (page-1);
        return sqlSession.getMapper(UserMapper.class).showFollowing(id, start);
    }

    @Override
    public List<User> showFollower(int id, int page) {
        int start = 18 * (page-1);
        return sqlSession.getMapper(UserMapper.class).showFollower(id, start);
    }

    @Override
    public List<follow> beforeFollowList(int id, int page) {
        int start = 18 * (page -1);
        return sqlSession.getMapper(UserMapper.class).beforeFollowList(id, start);
    }

    @Override
    public int showFollowingCnt(int followerId) {
        return sqlSession.getMapper(UserMapper.class).showFollowedCnt(followerId);
    }

    @Override
    public int showFollowerCnt(int followedId) {
        return sqlSession.getMapper(UserMapper.class).showFollowerCnt(followedId);
    }

    @Override
    public List<String> getPreferedArea(int userId) {
        return sqlSession.getMapper(UserMapper.class).getPreferedArea(userId);
    }

    @Override
    public List<String> getPreferedStyle(int userId) {
        return sqlSession.getMapper(UserMapper.class).getPreferedStyle(userId);
    }

    @Override
    public void makePreferredArea(PreferredArea preferredArea) {
        preferredArea.setArea(preferredArea.getArea().trim());
        sqlSession.getMapper(UserMapper.class).makePreferredArea(preferredArea);
    }

    @Override
    public void makePreferredStyle(PreferredStyle preferredStyle) {
        preferredStyle.setStyle(preferredStyle.getStyle().trim());
        sqlSession.getMapper(UserMapper.class).makePreferredStyle(preferredStyle);
    }

    @Override
    public List<PreferredArea> getPreferedAreaAll(int userId) {
        return sqlSession.getMapper(UserMapper.class).getPreferedAreaAll(userId);
    }

    @Override
    public void deletePreferedArea(PreferredArea preferredArea) {
        sqlSession.getMapper(UserMapper.class).deletePreferredArea(preferredArea);
    }

    @Override
    public void deletePreferedStyle(PreferredStyle preferredStyle) {
        sqlSession.getMapper(UserMapper.class).deletePreferredStyle(preferredStyle);
    }

    @Override
    public void modifyPassword(User user) {
        sqlSession.getMapper(UserMapper.class).modifyPassword(user);
    }


}
