package egovframework.example.sample.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.sample.service.UserService;
import egovframework.example.sample.service.UserVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("userService")
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService{
	
	@Resource(name = "PostDAO")
	private PostDAO postDAO;
	
	@Override
	public boolean validateUser(UserVO user) throws Exception {
		if (user == null || user.getUserId() == null || user.getPassword() == null) {
			return false;
		}

		UserVO savedUser = postDAO.selectUser(user.getUserId());
		if (savedUser == null) {
			return postDAO.insertUser(user) > 0;
		}

		return savedUser.getPassword().equals(user.getPassword());
	}
}
