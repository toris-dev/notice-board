package egovframework.example.post.presentation;

import java.util.List;

import egovframework.example.comment.application.CommentService;
import egovframework.example.comment.dto.CommentVO;
import egovframework.example.post.application.PostService;
import egovframework.example.post.dto.PostVO;
import egovframework.example.post.dto.SearchVO;
import egovframework.example.user.application.UserService;
import egovframework.example.user.dto.UserVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

@Controller
public class PostController {
	@Resource(name = "postService")
	private PostService postService;

	@Resource(name = "commentService")
	private CommentService commentService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	// 게시글 목록 조회
	@GetMapping("/list.do")
	public String getPostList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model) throws Exception {
		// 페이징 설정
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		// 첫 번째 인덱스와 마지막 인덱스 설정
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 게시글 목록 조회
		List<PostVO> postList = postService.getAllPosts(searchVO);
		model.addAttribute("resultList", postList);

		// 총 게시글 수 조회
		int totCnt = postService.selectPostListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "board/list";
	}

	// 게시글 상세 조회
	@GetMapping("/detailView.do")
	public String getPost(@RequestParam("postId") String postId, Model model) throws Exception {
		PostVO post = postService.getPost(postId);
		List<CommentVO> comments = commentService.getCommentsByPostId(postId); // 댓글 조회는 CommentService로
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		return "board/detailView";
	}

	// 게시글 작성 페이지
	@GetMapping("/write.do")
	public String writePostForm(Model model) throws Exception {
		model.addAttribute("postVO", new PostVO());
		return "board/write";
	}

	// 게시글 등록
	@PostMapping("/write")
	public String writePost(@ModelAttribute("postVO") PostVO postVO, @ModelAttribute("userVO") UserVO userVO,
			BindingResult result, Model model) throws Exception {

		beanValidator.validate(postVO, result);
		if (result.hasErrors()) {
			return "board/write";
		}

		if (userService.validateUser(userVO)) {
			postService.createPost(postVO, userVO);
			return "redirect:/board/list.do";
		} else {
			model.addAttribute("message", "Invalid user credentials");
			return "board/write";
		}
	}

	// 게시글 수정 페이지
	@GetMapping("/edit.do")
	public String editPostForm(@RequestParam("postId") String postId, Model model) throws Exception {
		PostVO post = postService.getPost(postId);
		model.addAttribute("post", post);
		return "board/edit";
	}

	// 게시글 수정
	@PostMapping("/edit.do")
	public String editPost(@ModelAttribute("postVO") PostVO postVO, @ModelAttribute("userVO") UserVO userVO,
			BindingResult result, Model model) throws Exception {

		beanValidator.validate(postVO, result);
		if (result.hasErrors()) {
			return "board/edit";
		}

		if (postService.updatePost(postVO, userVO)) {
			return "redirect:/board/view.do?postId=" + postVO.getPostId();
		} else {
			model.addAttribute("message", "Invalid user credentials");
			return "board/edit";
		}
	}

	// 게시글 삭제
	@DeleteMapping(value = "/delete.do")
	public String deletePost(@RequestParam("postId") String postId, @ModelAttribute("userVO") UserVO userVO,
			Model model) throws Exception {

		if (postService.deletePost(postId, userVO)) {
			return "redirect:/board/list.do";
		} else {
			model.addAttribute("message", "Invalid user credentials");
			return "redirect:/board/view.do?postId=" + postId;
		}
	}
}
