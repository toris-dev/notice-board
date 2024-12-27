package egovframework.example.sample.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import egovframework.example.sample.service.PostService;
import egovframework.example.sample.service.SearchVO;
import egovframework.example.sample.service.UserVO;
import egovframework.example.sample.service.PostVO;
import egovframework.example.sample.service.UserService;
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
	    private UserService userService;  // UserService 주입

	    @Override
	    public int createPost(PostVO post, UserVO user) throws Exception {
	        post.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	        post.setUpdatedAt(post.getCreatedAt());
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

	        post.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
