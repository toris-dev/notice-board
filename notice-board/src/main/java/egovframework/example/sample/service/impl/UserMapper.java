package egovframework.example.sample.service.impl;

import egovframework.example.sample.service.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("userMapper")
public interface UserMapper {
    // 사용자 인증 관련
    /**
     * 사용자 정보를 검증한다.
     * @param user
     * @return
     * @throws Exception
     */
    boolean validateUser(UserVO user) throws Exception;
}
