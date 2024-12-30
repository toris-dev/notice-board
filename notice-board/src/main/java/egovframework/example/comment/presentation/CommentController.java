package egovframework.example.comment.presentation;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import egovframework.example.comment.application.CommentService;
import egovframework.example.comment.dto.CommentVO;
import egovframework.example.user.dto.UserVO;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Resource(name = "commentService")
	private CommentService commentService;

	// 댓글 작성
	@PostMapping("/write.do")
	@ResponseBody
	public String writeComment(@ModelAttribute("commentVO") CommentVO commentVO,
			@ModelAttribute("userVO") UserVO userVO) throws Exception {
		int postId = commentService.addComment(commentVO, userVO);
		if (postId != -1) {
			return "redirect:/board/view.do?postId=" + postId;
		} else {
			return "redirect:/board/list.do";
		}
	}

	// 댓글 수정
	@PostMapping("/edit.do")
	@ResponseBody
	public boolean editComment(@ModelAttribute("commentVO") CommentVO commentVO,
			@ModelAttribute("userVO") UserVO userVO) throws Exception {
		return commentService.updateComment(commentVO, userVO);
	}

	// 댓글 삭제
	@DeleteMapping("/delete.do")
	@ResponseBody
	public boolean deleteComment(@RequestParam("commentId") String commentId, @ModelAttribute("userVO") UserVO userVO)
			throws Exception {
		return commentService.deleteComment(commentId, userVO);
	}
}
