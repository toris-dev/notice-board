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

import java.util.List;

import egovframework.example.sample.service.PostListVO;
import egovframework.example.sample.service.UserVO;
import egovframework.example.sample.service.CommentVO;
import egovframework.example.sample.service.PostVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;

/**
 * @Class Name : SampleDAO.java
 * @Description : Sample DAO Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Repository("sampleDAO")
public class PostDAO extends EgovAbstractDAO {
    // 게시글 관련 메서드
    public String insertPost(PostVO vo) throws Exception {
        return (String) insert("PostDAO.insertPost", vo);
    }
    
    public PostVO selectPost(String postId) throws Exception {
        return (PostVO) select("PostDAO.selectPost", postId);
    }
    
    public List<?> selectPostList(PostListVO searchVO) throws Exception {
        return list("PostDAO.selectPostList", searchVO);
    }
    
    public int selectPostListTotCnt(PostListVO searchVO) throws Exception {
        return (Integer) select("PostDAO.selectPostListTotCnt", searchVO);
    }
    
    public int updatePost(PostVO vo) throws Exception {
        return update("PostDAO.updatePost", vo);
    }
    
    public int deletePost(String postId) throws Exception {
        // 게시글 삭제 시 관련 댓글도 모두 삭제
        delete("PostDAO.deleteCommentsByPostId", postId);
        return delete("PostDAO.deletePost", postId);
    }
    
    // 댓글 관련 메서드
    public String insertComment(CommentVO vo) throws Exception {
        return (String) insert("PostDAO.insertComment", vo);
    }
    
    public CommentVO selectComment(String commentId) throws Exception {
        return (CommentVO) select("PostDAO.selectComment", commentId);
    }
    
    public List<CommentVO> selectCommentsByPostId(String postId) throws Exception {
        return (List<CommentVO>) list("PostDAO.selectCommentsByPostId", postId);
    }
    
    public int updateComment(CommentVO vo) throws Exception {
        return update("PostDAO.updateComment", vo);
    }
    
    public int deleteComment(String commentId) throws Exception {
        return delete("PostDAO.deleteComment", commentId);
    }
    
    public int deleteCommentsByPostId(String postId) throws Exception {
        return delete("PostDAO.deleteCommentsByPostId", postId);
    }
    
    // 사용자 관련 메서드
    public UserVO selectUser(String userId) throws Exception {
        return (UserVO) select("PostDAO.selectUser", userId);
    }
    
    public int insertUser(UserVO vo) throws Exception {
        return (Integer) insert("PostDAO.insertUser", vo);
    }

}