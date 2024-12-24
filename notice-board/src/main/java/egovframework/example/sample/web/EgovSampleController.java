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
package egovframework.example.sample.web;

import java.util.List;

import egovframework.example.sample.service.CommentVO;
import egovframework.example.sample.service.PostService;
import egovframework.example.sample.service.PostListVO;
import egovframework.example.sample.service.UserVO;
import egovframework.example.sample.service.PostVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
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

@Controller
public class EgovSampleController {

	@Resource(name = "anonymouspostService")
	private PostService postService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	// 게시글 목록 조회
	@RequestMapping(value = "/list.do")
	public String getPostList(@ModelAttribute("searchVO") PostListVO searchVO, ModelMap model) throws Exception {
		// 페이징 설정
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> postList = postService.getAllPosts(searchVO);
		model.addAttribute("resultList", postList);

		int totCnt = postService.selectPostListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "board/list";
	}

	// 게시글 상세 조회
	@RequestMapping(value = "/view.do")
	public String getPost(@RequestParam("postId") String postId, Model model) throws Exception {
		PostVO post = postService.getPost(postId);
		List<CommentVO> comments = postService.getCommentsByPostId(postId);

		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		return "board/view";
	}

	// 게시글 작성 페이지
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String writePostForm(Model model) throws Exception {
		model.addAttribute("postVO", new PostVO());
		return "board/write";
	}

	// 게시글 등록
	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String writePost(@ModelAttribute("postVO") PostVO postVO, @ModelAttribute("userVO") UserVO userVO,
			BindingResult result, Model model) throws Exception {

		beanValidator.validate(postVO, result);
		if (result.hasErrors()) {
			return "board/write";
		}

		if (postService.validateUser(userVO)) {
			postService.createPost(postVO, userVO);
			return "redirect:/board/list.do";
		} else {
			model.addAttribute("message", "Invalid user credentials");
			return "board/write";
		}
	}

	// 게시글 수정 페이지
	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public String editPostForm(@RequestParam("postId") String postId, Model model) throws Exception {
		PostVO post = postService.getPost(postId);
		model.addAttribute("post", post);
		return "board/edit";
	}

	// 게시글 수정
	@RequestMapping(value = "/edit.do", method = RequestMethod.POST)
	public String editPost(@ModelAttribute("postVO") PostVO postVO, @ModelAttribute("userVO") UserVO userVO,
			BindingResult result, Model model) throws Exception {

		beanValidator.validate(postVO, result);
		if (result.hasErrors()) {
			return "board/edit";
		}

		if (postService.updatePost(postVO, userVO)) {
			return "redirect:/board/view.do?postId=" + postVO.getBoardId();
		} else {
			model.addAttribute("message", "Invalid user credentials");
			return "board/edit";
		}
	}

	// 게시글 삭제
	@RequestMapping(value = "/delete.do")
	public String deletePost(@RequestParam("postId") String postId, @ModelAttribute("userVO") UserVO userVO,
			Model model) throws Exception {

		if (postService.deletePost(postId, userVO)) {
			return "redirect:/board/list.do";
		} else {
			model.addAttribute("message", "Invalid user credentials");
			return "redirect:/board/view.do?postId=" + postId;
		}
	}

	// 댓글 작성
	@RequestMapping(value = "/comment/write.do", method = RequestMethod.POST)
	@ResponseBody
	public String writeComment(@ModelAttribute("commentVO") CommentVO commentVO,
			@ModelAttribute("userVO") UserVO userVO) throws Exception {

		String postId = postService.addComment(commentVO, userVO);
		if(postId.isEmpty()) {
			return "redirect:/board/view.do?postId=" + postId;
		} else {
			return "redirect:/board/list.do";
		}
	}

	// 댓글 수정
	@RequestMapping(value = "/comment/edit.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean editComment(@ModelAttribute("commentVO") CommentVO commentVO,
			@ModelAttribute("userVO") UserVO userVO) throws Exception {

		return postService.updateComment(commentVO, userVO);
	}

	// 댓글 삭제
	@RequestMapping(value = "/comment/delete.do")
	@ResponseBody
	public boolean deleteComment(@RequestParam("commentId") String commentId, @ModelAttribute("userVO") UserVO userVO)
			throws Exception {

		return postService.deleteComment(commentId, userVO);
	}
}
