package com.velog.velogproject;

import com.velog.velogproject.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VelogProjectApplicationTests {

	@Autowired
	private UserService userService;

//	@Test
//	@DisplayName("로그인 서비스 테스트")
//	void loginServiceTest() {
//		String email = "test@example";
//		String password = "password";
//
//		// userService.login() 메소드 호출
//		boolean isAuthenticated = userServiceImpl.login(email, password);
//
//		// userService.login()이 true를 반환하는지 확인
//		assertTrue(isAuthenticated, "로그인 서비스 테스트 실패: 유효한 계정으로 로그인 시도");
//	}

//	@Test
//	@DisplayName("회원가입 잘 되는지 테스트")
//	void registerServiceTest(){
//		userService.register("test@test.com", "password");
//	}
}
