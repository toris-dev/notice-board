package egovframework.example.user.mapper;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import egovframework.example.user.dto.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("UserMapper")
public interface UserMapper {

	// userId로 사용자 조회
	UserVO getUserById(String userId) throws Exception;

	// username으로 사용자 조회
	UserVO getUserByUsername(String username) throws UsernameNotFoundException;

	// 사용자 삽입
	void insertUser(UserVO user) throws Exception;

	// 사용자 정보 수정
	void updateUser(UserVO user) throws Exception;
}