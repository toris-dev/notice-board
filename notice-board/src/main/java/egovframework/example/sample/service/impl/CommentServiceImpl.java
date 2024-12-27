package egovframework.example.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.sample.service.CommentService;
import egovframework.example.sample.service.CommentVO;
import egovframework.example.sample.service.UserVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("commentService")
public class CommentServiceImpl extends EgovAbstractServiceImpl implements CommentService {

    @Resource(name="PostDAO")
    private PostDAO PostDAO;

    @Resource(name="CommentDAO")
    private CommentDAO CommentDAO;
    @Override
    public int addComment(CommentVO comment, UserVO user) throws Exception {
        comment.setAuthorId(user.getUserId());
        return CommentDAO.insertComment(comment);
    }

    @Override
    public boolean updateComment(CommentVO comment, UserVO user) throws Exception {
        CommentVO originalComment = CommentDAO.selectComment(comment.getCommentId());
        if (!validateUser(user) || !originalComment.getAuthorId().equals(user.getUserId())) {
            return false;
        }

        return CommentDAO.updateComment(comment) > 0;
    }

    @Override
    public boolean deleteComment(String commentId, UserVO user) throws Exception {
        CommentVO comment = CommentDAO.selectComment(commentId);
        if (!validateUser(user) || !comment.getAuthorId().equals(user.getUserId())) {
            return false;
        }

        return CommentDAO.deleteComment(commentId) > 0;
    }

    @Override
    public List<CommentVO> getCommentsByPostId(String postId) throws Exception {
        return CommentDAO.selectCommentsByPostId(postId);
    }

    @Override
    public boolean validateUser(UserVO user) throws Exception {
        if (user == null || user.getUserId() == null || user.getPassword() == null) {
            return false;
        }

        UserVO savedUser = PostDAO.selectUser(user.getUserId());
        if (savedUser == null) {
            return PostDAO.insertUser(user) > 0;
        }

        return savedUser.getPassword().equals(user.getPassword());
    }
}