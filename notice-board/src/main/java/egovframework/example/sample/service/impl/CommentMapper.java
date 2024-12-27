package egovframework.example.sample.service.impl;

import java.util.List;

import egovframework.example.sample.service.CommentVO;
import egovframework.example.sample.service.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("CommentMapper")
public interface CommentMapper {
	// 댓글 관련
	/**
	 * 하나의 댓글을 추가한다.
	 * 
	 * @param comment
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int addComment(CommentVO comment, UserVO user) throws Exception;

	/**
	 * 댓글을 수정한다.
	 * 
	 * @param comment
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean updateComment(CommentVO comment, UserVO user) throws Exception;

	/**
	 * 댓글을 삭제한다.
	 * 
	 * @param commentId
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean deleteComment(String commentId, UserVO user) throws Exception;

	/**
	 * 해당하는 게시글의 댓글을 가져온다.
	 * 
	 * @param postId
	 * @return
	 * @throws Exception
	 */
	List<CommentVO> getCommentsByPostId(String postId) throws Exception;

}
