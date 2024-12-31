package egovframework.example.auth.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

	// 로그인 페이지를 반환
	@GetMapping("/signin.do")
	public String signin(@RequestParam(value = "error", required = false) String error) {
		if (error != null) {
			return "signin"; // 로그인 실패 시 에러 메시지를 전달
		}
		return "signin"; // 로그인 페이지를 반환
	}

	// 회원가입 페이지를 반환
	@GetMapping("/signup.do")
	public String signup() {
		return "signup"; // 회원가입 페이지 반환
	}

}
