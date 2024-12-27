package egovframework.example.sample.service.impl;

import java.util.List;

import egovframework.example.sample.service.SearchVO;
import egovframework.example.sample.service.PostVO;
import egovframework.example.sample.service.UserVO;

import org.springframework.stereotype.Repository;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("PostDAO")
public class PostDAO extends EgovAbstractMapper {

    // 게시글 관련 메서드
    public int insertPost(PostVO vo) throws Exception {
        return insert("insertPost", vo);
    }

    public PostVO getPost(String postId) throws Exception {
        return selectOne("getPost", postId);
    }

    public List<PostVO> getAllPosts(SearchVO searchVO) throws Exception {
        return selectList("getAllPosts", searchVO);
    }

    public int selectPostListTotCnt(SearchVO searchVO) throws Exception {
        return selectOne("selectPostListTotCnt", searchVO);
    }

    public int updatePost(PostVO vo) throws Exception {
        return update("updatePost", vo);
    }

    public int deletePost(String postId) throws Exception {
        return delete("deletePost", postId);
    }
    
    // 사용자 관련 ID로 게시물  찾기
    public UserVO selectUser(String userId) throws Exception {
        return selectOne("selectUser", userId);
    }

    public int insertUser(UserVO vo) throws Exception {
        return insert("insertUser", vo);
    }
}
