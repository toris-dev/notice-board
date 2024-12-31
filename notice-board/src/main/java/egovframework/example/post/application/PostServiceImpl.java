package egovframework.example.post.application;

import java.util.Date;
import java.util.List;

import egovframework.example.user.application.UserService;
import egovframework.example.user.dto.UserVO;
import egovframework.example.comment.dao.CommentDAO;
import egovframework.example.post.dao.PostDAO;
import egovframework.example.post.dto.PostVO;
import egovframework.example.post.dto.SearchVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("postService")
public class PostServiceImpl extends EgovAbstractServiceImpl implements PostService {
	@Resource(name = "PostDAO")
	private PostDAO postDAO;

	@Resource(name = "CommentDAO")
	private CommentDAO commentDAO;

	@Resource(name = "userService")
	private UserService userService; // UserService 주입

	@Override
	public int createPost(PostVO post, UserVO user) throws Exception {
		// 생성일과 수정일을 Date 타입으로 설정
		Date currentDate = new Date();
		post.setCreatedAt(currentDate); // 생성일
		post.setUpdatedAt(currentDate); // 수정일
		post.setAuthorId(user.getUserId());

		return postDAO.insertPost(post);
	}

	@Override
	public PostVO getPost(String postId) throws Exception {
		return postDAO.getPost(postId);
	}

	@Override
	public List<PostVO> getAllPosts(SearchVO searchVO) throws Exception {
		return postDAO.getAllPosts(searchVO);
	}

	@Override
	public boolean updatePost(PostVO post, UserVO user) throws Exception {
		PostVO originalPost = postDAO.getPost(post.getPostId());
		if (!userService.validateUser(user) || !originalPost.getAuthorId().equals(user.getUserId())) {
			return false;
		}

		// 수정일을 현재 날짜로 설정
		Date currentDate = new Date();
		post.setUpdatedAt(currentDate);

		return postDAO.updatePost(post) > 0;
	}

	@Override
	public boolean deletePost(String postId, UserVO user) throws Exception {
		PostVO post = postDAO.getPost(postId);
		if (!userService.validateUser(user) || !post.getAuthorId().equals(user.getUserId())) {
			return false;
		}

		commentDAO.deleteCommentsByPostId(postId); // 댓글도 삭제
		return postDAO.deletePost(postId) > 0;
	}

	@Override
	public int selectPostListTotCnt(SearchVO searchVO) throws Exception {
		return postDAO.selectPostListTotCnt(searchVO);
	}
}
