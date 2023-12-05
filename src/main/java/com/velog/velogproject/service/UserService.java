package com.velog.velogproject.service;

import com.velog.velogproject.dto.response.LoginResponseDTO;
import com.velog.velogproject.dto.response.RegisterResponseDTO;

/**
 * 사용자 서비스의 비지니스 로직을 정의
 */
public interface UserService {

    /**
     * 사용자 로그인 인증 서비스
     * @param email : 이메일
     * @param password : 패스워드
     * @return LoginResponseDTO
     */
    LoginResponseDTO login(String email, String password);

    /**
     * 사용자 회원가입 서비스
     * @param
     * @param email : 사용할 이메일
     * @param password : 사용할 패스워드
     * @return RegisterResponseDTO
     */
    RegisterResponseDTO register(String email, String password, String profileName, String profile);


}
