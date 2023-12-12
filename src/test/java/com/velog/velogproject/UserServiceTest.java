package com.velog.velogproject;

import com.velog.velogproject.dto.response.UserResponseDTO;
import com.velog.velogproject.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void 회원가입_테스트(){
        userService.register(
                "test@example.com",
                "password",
                "테스트 유저",
                "테스트 프로필"
        );
    }

    @Test
    public void 로그인_테스트() {
        userService.login(
                "test@example.com",
                "password"
        );
    }

    @Test @Transactional
    public void 회원탈퇴_테스트() {
        UserResponseDTO.Login user = userService.login(
                "test@example.com",
                "password"
        );

        userService.withdraw(user.getUserId());
    }

}
