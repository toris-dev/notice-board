package egovframework.example.user.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import egovframework.example.user.dto.UserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("UserDAO")
public class UserDAO extends EgovAbstractMapper {

	// 사용자 ID로 User 정보 조회
	public UserVO getUserById(String userId) throws Exception {
		return selectOne("getUserById", userId);
	}

	// 사용자 이름으로 User 정보 조회
	public UserVO getUserByUserId(String userId) throws UsernameNotFoundException {
		return selectOne("getUserByUserId", userId);
	}

	// 사용자 정보 추가
	public int insertUser(UserVO user) throws Exception {
		return insert("insertUser", user);
	}

	// 사용자 정보 업데이트
	public int updateUser(UserVO user) throws Exception {
		return update("updateUser", user);
	}
}
