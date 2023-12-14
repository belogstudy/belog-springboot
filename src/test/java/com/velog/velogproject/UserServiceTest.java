package com.velog.velogproject;

import com.velog.velogproject.dto.response.UserResponseDTO;
import com.velog.velogproject.entity.UserInfoEntity;
import com.velog.velogproject.repository.UserRepository;
import com.velog.velogproject.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest @Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

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

    @Test
    public void 유저_찾기() {
        UUID userId = UUID.fromString("e20ac609-f9b7-48a4-8d48-9d4ca4876816");
        UserInfoEntity user = userRepository.findByUserId(userId);

        log.info("찾은 유저 정보: {}", user.getUserId());

        UserResponseDTO.Info userDto = userService.getUserInfo(userId);

        log.info("찾은 유저 정보: {}", userDto.getUserId());
    }

}
