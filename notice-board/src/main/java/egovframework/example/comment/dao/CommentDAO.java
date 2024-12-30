package egovframework.example.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.comment.dto.CommentVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("CommentDAO")
public class CommentDAO extends EgovAbstractMapper {
    // 댓글 관련 메서드
    public int insertComment(CommentVO vo) throws Exception {
        return insert("PostDAO.insertComment", vo);
    }

    public CommentVO selectComment(String commentId) throws Exception {
        return selectOne("selectComment", commentId);
    }

    public List<CommentVO> selectCommentsByPostId(String postId) throws Exception {
        return selectList("selectCommentsByPostId", postId);
    }

    public int updateComment(CommentVO vo) throws Exception {
        return update("updateComment", vo);
    }

    public int deleteComment(String commentId) throws Exception {
        return delete("deleteComment", commentId);
    }

    public int deleteCommentsByPostId(String postId) throws Exception {
        return delete("deleteCommentsByPostId", postId);
    }
}
