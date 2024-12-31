package egovframework.example.user.application;

import java.util.Collections;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import egovframework.example.auth.totp.TOTPUtil;
import egovframework.example.user.dao.UserDAO;
import egovframework.example.user.dto.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "UserDAO")
	private UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder; // PasswordEncoder 추가

	@Override
	public UserVO getUserById(String userId) throws Exception {
		return userDAO.getUserById(userId);
	}

	@Override
	public UserVO getUserByUsername(String userId) throws UsernameNotFoundException {
		UserVO user = userDAO.getUserByUserId(userId);
		if (user == null) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}
		return user;
	}

	// 회원가입
	public boolean createUser(UserVO user) throws Exception {
		// 비밀번호 암호화
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);

		// 사용자 삽입
		return userDAO.insertUser(user) > 0;
	}

	@Override
	public boolean enableOtp(String userId) throws Exception {
		UserVO user = userDAO.getUserById(userId);
		if (user != null) {
			String otpSecret = TOTPUtil.generateSecretKey();
			user.setOtpEnabled(true);
			user.setOtpSecret(otpSecret);
			return userDAO.updateUser(user) > 0;
		}
		return false;
	}

	@Override
	public boolean validateOtp(String userId, String otpCode) throws Exception {
		UserVO user = userDAO.getUserById(userId);
		if (user != null && user.isOtpEnabled()) {
			return TOTPUtil.validateTOTP(user.getOtpSecret(), otpCode);
		}
		return false;
	}

	// 추가된 메서드: 사용자 유효성 검사 (ID와 비밀번호 확인)
	@Override
	public boolean validateUser(UserVO userVO) throws Exception {
		UserVO user = userDAO.getUserById(userVO.getUserId());
		if (user != null) {
			// 비밀번호가 일치하는지 확인
			return passwordEncoder.matches(userVO.getPassword(), user.getPassword());
		}
		return false;
	}

	// 로그인 검증
	@Override
	public boolean validateLogin(String userId, String password) throws Exception {
		UserVO user = userDAO.getUserById(userId); // 사용자 조회
		return passwordEncoder.matches(password, user.getPassword()); // 암호화된 비밀번호 비교
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		try {
			UserVO user = userDAO.getUserById(userId);
			if (user == null) {
				throw new UsernameNotFoundException("User not found with username: " + userId);
			}

			return new User(user.getUserId(), user.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
		} catch (Exception e) {
			throw new UsernameNotFoundException("Error loading user", e);
		}
	}
}
