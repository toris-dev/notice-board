package egovframework.example.user.application;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import egovframework.example.user.dto.UserVO;

public interface UserService extends UserDetailsService {
	UserVO getUserById(String userId) throws Exception;

	UserVO getUserByUsername(String username) throws UsernameNotFoundException;

	boolean createUser(UserVO user) throws Exception;

	boolean enableOtp(String userId) throws Exception;

	boolean validateOtp(String userId, String otpCode) throws Exception;

	public boolean validateUser(UserVO userVO) throws Exception;

	public boolean validateLogin(String userId, String password) throws Exception;
}