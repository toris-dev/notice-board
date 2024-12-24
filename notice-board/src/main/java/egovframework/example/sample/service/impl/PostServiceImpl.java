/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import egovframework.example.sample.service.CommentVO;
import egovframework.example.sample.service.PostService;
import egovframework.example.sample.service.PostListVO;
import egovframework.example.sample.service.UserVO;
import egovframework.example.sample.service.PostVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
 * @Modification Information
 * @ @ 수정일 수정자 수정내용 @ --------- --------- ------------------------------- @
 *   2009.03.16 최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 * 		Copyright (C) by MOPAS All right reserved.
 */

@Service("postService")
public class PostServiceImpl extends EgovAbstractServiceImpl implements PostService {

	   @Resource(name="postDAO")
	    private PostDAO PostDAO;
	    
	    @Override
	    public String createPost(PostVO post, UserVO user) throws Exception {
	        // 기본값 설정
	        post.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	        post.setUpdated_at(post.getCreatedAt());
	        post.setAuthorId(user.getId());
	        
	        return PostDAO.insertPost(post);
	    }
	    
	    @Override
	    public PostVO getPost(String postId) throws Exception {
	        return PostDAO.selectPost(postId);
	    }
	    
	    @Override
	    public List<?> getAllPosts(PostListVO searchVO) throws Exception {
	        return PostDAO.selectPostList(searchVO);
	    }
	    
	    @Override
	    public boolean updatePost(PostVO post, UserVO user) throws Exception {
	        // 작성자 검증
	        PostVO originalPost = PostDAO.selectPost(post.getBoardId());
	        if (!validateUser(user) || !originalPost.getAuthorId().equals(user.getId())) {
	            return false;
	        }
	        
	        post.setUpdated_at(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	        return PostDAO.updatePost(post) > 0;
	    }
	    
	    @Override
	    public boolean deletePost(String postId, UserVO user) throws Exception {
	        // 작성자 검증
	        PostVO post = PostDAO.selectPost(postId);
	        if (!validateUser(user) || !post.getAuthorId().equals(user.getId())) {
	            return false;
	        }
	        
	        return PostDAO.deletePost(postId) > 0;
	    }
	    
	    @Override
	    public String addComment(CommentVO comment, UserVO user) throws Exception {
	        comment.setAuthorId(user.getId());
	        return PostDAO.insertComment(comment);
	    }
	    
	    @Override
	    public boolean updateComment(CommentVO comment, UserVO user) throws Exception {
	        // 작성자 검증
	        CommentVO originalComment = PostDAO.selectComment(comment.getCommentId());
	        if (!validateUser(user) || !originalComment.getAuthorId().equals(user.getId())) {
	            return false;
	        }
	        
	        return PostDAO.updateComment(comment) > 0;
	    }
	    
	    @Override
	    public boolean deleteComment(String commentId, UserVO user) throws Exception {
	        // 작성자 검증
	        CommentVO comment = PostDAO.selectComment(commentId);
	        if (!validateUser(user) || !comment.getAuthorId().equals(user.getId())) {
	            return false;
	        }
	        
	        return PostDAO.deleteComment(commentId) > 0;
	    }
	    
	    @Override
	    public int selectPostListTotCnt(PostListVO searchVO) throws Exception {
	        return PostDAO.selectPostListTotCnt(searchVO);
	    }
	    
	    @Override
	    public List<CommentVO> getCommentsByPostId(String postId) throws Exception {
	        return PostDAO.selectCommentsByPostId(postId);
	    }
	    
	    @Override
	    public boolean validateUser(UserVO user) throws Exception {
	        // ID와 비밀번호만으로 검증
	        if (user == null || user.getId() == null || user.getPassword() == null) {
	            return false;
	        }
	        
	        UserVO savedUser = PostDAO.selectUser(user.getId());
	        if (savedUser == null) {
	            // 새로운 사용자인 경우 등록
	            return PostDAO.insertUser(user) > 0;
	        }
	        
	        // 기존 사용자인 경우 비밀번호 확인
	        return savedUser.getPassword().equals(user.getPassword());
	    }
}