package com.been.beenbackend.Service;

import com.been.beenbackend.Mapper.PostMapper;
import com.been.beenbackend.dto.Like;
import com.been.beenbackend.dto.Post;
import com.been.beenbackend.dto.PostPic;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Post> list() {
        return sqlSession.getMapper(PostMapper.class).list();
    }

    @Override
    public List<Post> listByUser(int userId) {
        return sqlSession.getMapper(PostMapper.class).listByUser(userId);
    }

    @Override
    public int register(Post post) {
        sqlSession.getMapper(PostMapper.class).register(post);
        int postId = post.getPostId();
        return postId;

    }

    @Override
    public void modify(Post post) {
        sqlSession.getMapper(PostMapper.class).modify(post);

    }

    @Override
    public void delete(int postId) {
        sqlSession.getMapper(PostMapper.class).delete(postId);

    }

    @Override
    public Post listOne(int postId) {
        return sqlSession.getMapper(PostMapper.class).listOne(postId);

    }

    @Override
    public List<Post> showFollowPost(int id) {
        return sqlSession.getMapper(PostMapper.class).showFollowPost(id);
    }

    @Override
    public int makeLike(int postId, int userId) {
        return sqlSession.getMapper(PostMapper.class).makeLike(postId, userId);
    }

    @Override
    public void removeLke(int postId, int userId) {
        sqlSession.getMapper(PostMapper.class).removeLike(postId, userId);
    }

    @Override
    public List<Post> likePost(int userId) {
        return sqlSession.getMapper(PostMapper.class).likePost(userId);
    }

    @Override
    public int registerPic(PostPic postPic) {
        return sqlSession.getMapper(PostMapper.class).registerPic(postPic);
    }

    @Override
    public List<PostPic> getPostPic(int postId) {
        return sqlSession.getMapper(PostMapper.class).getPostPic(postId);
    }

    @Override
    public void deletePic(int postId) {
        sqlSession.getMapper(PostMapper.class).deletePic(postId);
    }

    @Override
    public int showLikeyCnt(int postId) {
        return sqlSession.getMapper(PostMapper.class).showLikeyCnt(postId);
    }

    @Override
    public Like getLikey(int postId, int userId) {
        return sqlSession.getMapper(PostMapper.class).getLikey(postId, userId);
    }

    @Override
    public List<Post> searchByTitle(String title) {
        title = "%"+title+"%";
        return sqlSession.getMapper(PostMapper.class).searchByTitle(title);
    }

    @Override
    public List<Post> showPreferedAreaPost(List<String> areas) {
        StringBuilder areaList = new StringBuilder();
        for(String area:areas) {
//            area = "%"+area+"%";
            areaList.append(area.trim()+"|");
        }
        areaList.deleteCharAt(areaList.lastIndexOf("|"));
        String result = areaList.toString();
        System.out.println(result);
        return sqlSession.getMapper(PostMapper.class).showPreferedAreaPost(result);
    }

    @Override
    public List<Post> showPreferedStylePost(List<String> styles) {
        StringBuilder styleList = new StringBuilder();
        for(String style:styles) {
//            area = "%"+area+"%";
//            System.out.println(style.trim());
            styleList.append(style.trim()+"|");
        }
        styleList.deleteCharAt(styleList.lastIndexOf("|"));
        String result = styleList.toString();
        System.out.println(result);
        return sqlSession.getMapper(PostMapper.class).showPreferedStylePost(result);
    }
}
