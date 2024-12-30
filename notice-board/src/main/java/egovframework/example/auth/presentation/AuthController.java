package egovframework.example.auth.presentation;

import egovframework.example.user.application.UserService;
import egovframework.example.user.dto.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // 로그인 페이지를 반환
    @GetMapping("/signin.do")
    public String signin(@RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            return "signin";  // 로그인 실패 시 에러 메시지를 전달
        }
        return "signin";  // 로그인 페이지를 반환
    }

    @PostMapping("/signin.do")
    public String signin(String userId, String password) {
        try {
            // 로그인 검증
            boolean isValidUser = userService.validateLogin(userId, password);
            if (isValidUser) {
                return "redirect:/list.do";  // 로그인 성공 시 홈으로 리디렉션
            } else {
                return "redirect:/signin.do?error=true";  // 로그인 실패 시 에러 페이지로 리디렉션
            }
        } catch (Exception e) {
            return "redirect:/signin.do?error=true";  // 예외 발생 시 에러 페이지로 리디렉션
        }
    }

    // 로그아웃 처리 URL
    @GetMapping("/signout.do")
    public String signout() {
        return "redirect:/signin.do?signout";  
    }

    // 회원가입 페이지를 반환
    @GetMapping("/signup.do")
    public String signup() {
        return "signup";  // 회원가입 페이지 반환
    }

    // 회원가입 처리 (POST 요청)
    @PostMapping("/signup.do")
    public String signupProcess(String userId, String password, String confirmPassword, String nickname) {
        // 비밀번호와 확인 비밀번호가 일치하는지 확인
        if (!password.equals(confirmPassword)) {
            return "redirect:/signup.do?error=passwordMismatch";  
        }

        // 회원가입 처리 (DB 저장 등)
        UserVO user = new UserVO();
        user.setNickname(userId);
        user.setPassword(password); // 비밀번호는 암호화되어 저장됨
        user.setNickname(nickname);

        try {
            userService.createUser(user);
            return "redirect:/signin.do?signupSuccess";  // 회원가입 후 로그인 페이지로 리디렉션
        } catch (Exception e) {
            return "redirect:/signup.do?error=true";  // 오류 발생 시 회원가입 페이지로 리디렉션
        }
    }
}
