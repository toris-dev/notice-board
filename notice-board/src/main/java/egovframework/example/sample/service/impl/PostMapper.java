/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
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

import egovframework.example.sample.service.SearchVO;
import egovframework.example.sample.service.PostVO;
import egovframework.example.sample.service.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("PostMapper")
public interface PostMapper {
	/**
	 * 게시글 생성한다.
	 * @param post
	 * @param user
	 * @return
	 * @throws Exception
	 */
    int createPost(PostVO post, UserVO user) throws Exception;
    
    /**
     * 하나의 게시글 상세정보를 가져온다.
     * @param postId
     * @return
     * @throws Exception
     */
    PostVO getPost(String postId) throws Exception;
    
    /**
     * 여러개의 게시글 리스트를 가져온다.
     * @param searchVO
     * @return
     * @throws Exception
     */
    List<PostVO> getAllPosts(SearchVO searchVO) throws Exception;
    
    /**
     * 하나의 게시글을 수정한다.
     * @param post
     * @param user
     * @return
     * @throws Exception
     */
    boolean updatePost(PostVO post, UserVO user) throws Exception;
    
    /**
     * 하나의 게시글을 삭제한다.
     * @param postId
     * @param user
     * @return
     * @throws Exception
     */
    boolean deletePost(String postId, UserVO user) throws Exception;
    
    /**
     * 게시글 총 개수를 조회하는 메서드
     * @param searchVO
     * @return
     * @throws Exception
     */
    int selectPostListTotCnt(SearchVO searchVO) throws Exception; // 추가된 메서드
    


	

}
