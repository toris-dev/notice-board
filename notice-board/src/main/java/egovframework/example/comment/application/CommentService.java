package egovframework.example.comment.application;

import java.util.List;

import egovframework.example.comment.dto.CommentVO;
import egovframework.example.user.dto.UserVO;

public interface CommentService {
    // 댓글 관련
    /**
     * 하나의 댓글을 추가한다.
     * @param comment
     * @param user
     * @return
     * @throws Exception
     */
    int addComment(CommentVO comment, UserVO user) throws Exception;
    /**
     * 댓글을 수정한다.
     * @param comment
     * @param user
     * @return
     * @throws Exception
     */
    boolean updateComment(CommentVO comment, UserVO user) throws Exception;
    
    /**
     * 댓글을 삭제한다.
     * @param commentId
     * @param user
     * @return
     * @throws Exception
     */
    boolean deleteComment(String commentId, UserVO user) throws Exception;
    
    /**
     * 해당하는 게시글의 댓글을 가져온다.
     * @param postId
     * @return
     * @throws Exception
     */
    List<CommentVO> getCommentsByPostId(String postId) throws Exception;
    
    // 사용자 인증 관련
    /**
     * 사용자 정보를 검증한다.
     * @param user
     * @return
     * @throws Exception
     */
    boolean validateUser(UserVO user) throws Exception;
	
	
}
