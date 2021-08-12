package com.been.beenbackend.Service;

import com.been.beenbackend.Mapper.UserMapper;
import com.been.beenbackend.dto.User;
import com.been.beenbackend.dto.follow;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> findUserByEmail(String email) {
        email = "%"+email+"%";
        System.out.println(email);
        return sqlSession.getMapper(UserMapper.class).findUserByEmail(email);
    }

    @Override
    public List<User> findUserByNickname(String nickname) {
        nickname = "%"+nickname+"%";
        System.out.println(nickname);
        return sqlSession.getMapper(UserMapper.class).findUserByNickname(nickname);
    }

    @Override
    public void updatePic(User user) {
        sqlSession.getMapper(UserMapper.class).updatePic(user);
    }

    @Override
    public List<User> showFollowing(int id) {
        return sqlSession.getMapper(UserMapper.class).showFollowing(id);
    }

    @Override
    public List<User> showFollower(int id) {
        return sqlSession.getMapper(UserMapper.class).showFollower(id);
    }

    @Override
    public List<follow> beforeFollowList(int id) {
        return sqlSession.getMapper(UserMapper.class).beforeFollowList(id);
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


}
