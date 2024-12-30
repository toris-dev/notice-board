package egovframework.example.user.presentation;

import egovframework.example.user.application.UserService;
import egovframework.example.user.dto.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public String getUser(@PathVariable String userId, Model model) throws Exception {
		UserVO user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "user/detail";
	}

	@PostMapping("/create")
	public String createUser(@ModelAttribute UserVO user) throws Exception {
		userService.createUser(user);
		return "redirect:/user/list";
	}

	@PostMapping("/enable-otp/{userId}")
	public String enableOtp(@PathVariable String userId, Model model) throws Exception {
		boolean success = userService.enableOtp(userId);
		model.addAttribute("message", success ? "OTP enabled successfully." : "Failed to enable OTP.");
		return "redirect:/user/" + userId;
	}

	@PostMapping("/validate-otp")
	public String validateOtp(@RequestParam String userId, @RequestParam String otpCode, Model model) throws Exception {
		boolean isValid = userService.validateOtp(userId, otpCode);
		model.addAttribute("message", isValid ? "OTP validated successfully." : "Invalid OTP.");
		return "redirect:/user/" + userId;
	}
}
