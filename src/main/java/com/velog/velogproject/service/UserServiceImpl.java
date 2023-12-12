package com.velog.velogproject.service;

import com.velog.velogproject.dto.response.UserResponseDTO;
import com.velog.velogproject.entity.UserInfoEntity;
import com.velog.velogproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 사용자 서비스의 비지니스 로직을 구현
 */
@Service @Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO.Login login(String email, String password) {
        try {
            UserInfoEntity user = userRepository.findByEmailAndPassword(email, password);
            log.error("로그인 성공");

            // 로그인 성공 시 LoginResponseDTO 에 UserId를 담아 반환
            UserResponseDTO.Login dto = new UserResponseDTO.Login();
            dto.setUserId(user.getUserId());
            
            return dto;
        } catch (Exception e) {
            log.error("로그인 중 오류 발생: {}", e.getMessage(), e);

            // 로그인 실패 시 LoginResponseDTO에 errorMessage를 담아 반환
            UserResponseDTO.Login errorResponse = new UserResponseDTO.Login();
            errorResponse.setMessage("로그인 실패 : 사용자의 입력이 잘못 되었습니다.");
            return errorResponse;
        }
    }

    @Override
    public UserResponseDTO.Register register(String email, String password, String profileName, String profile) {
        // 새로운 사용자 생성
        UserInfoEntity newUser = UserInfoEntity.createBuilder()
                .email(email)
                .password(password)
                .profileName(profileName)
                .profile(profile)
                .build();

        try {
            // 데이터베이스에 저장
            UserInfoEntity saveUser = userRepository.save(newUser);
            log.info("성공적으로 사용자를 생성하였습니다. : {}",saveUser);
            
            // 성공 시, 생성된 UserId 와 Email 응답
            UserResponseDTO.Register dto = new UserResponseDTO.Register();
            dto.setUserId(saveUser.getUserId());
            dto.setEmail(saveUser.getEmail());
            return dto;

        } catch (Exception e){
            // 실패 시 에러 메시지를 포함한 응답
            log.error("에러 : "+ e.getMessage());
            UserResponseDTO.Register errorResponse = new UserResponseDTO.Register();
            errorResponse.setMessage("사용자 등록 실패: 중복된 이메일입니다.");
            return errorResponse;
        }
    }

    @Override
    public UserResponseDTO.Withdraw withdraw(UUID userId) {

        // 데이터 무결성을 위해서 유저를 삭제하는 것이 아닌, 비활성화.
        UserInfoEntity deleteUser = UserInfoEntity.deleteBuilder()
                .userId(userId)
                .deletedAt(LocalDateTime.now())
                .build();

        userRepository.save(deleteUser);

        return new UserResponseDTO.Withdraw(userId, "성공적으로 사용자를 비활성화 하였습니다.");
    }
}
