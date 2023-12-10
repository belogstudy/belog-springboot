package com.velog.velogproject.controller;

import com.velog.velogproject.dto.request.LoginRequestDTO;
import com.velog.velogproject.dto.request.RegisterRequestDTO;
import com.velog.velogproject.dto.response.LoginResponseDTO;
import com.velog.velogproject.dto.response.RegisterResponseDTO;
import com.velog.velogproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자(유저) 관련 RestAPI Controller 구현
 */
@RestController @Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    /** * 로그인 API */
    @Operation(summary = "로그인", description = "사용자의 이메일과 패스워드를 받아 로그인을 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "로그인 실패", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDTO.class))
            })
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        log.info("로그인 요청 : Email: {}, Password: {}",email, password);
            LoginResponseDTO responseDTO = userService.login(email,password); // 서비스에 로그인 요청을 보냄

            if(responseDTO != null && responseDTO.getUserId() != null) {
                log.info("로그인 성공 : Email: {}", email);
                return ResponseEntity.ok(responseDTO);
            }else {
                log.warn("로그인 실패 : 사용자의 입력이 잘못 되었습니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
        }
    }
    /** * 회원가입 API  */
    @Operation(summary = "회원가입", description = "사용자의 정보를 받아 회원가입을 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterResponseDTO.class))
            }),
            @ApiResponse(responseCode = "500", description = "회원가입 실패", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterResponseDTO.class))
            })
    })
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        // 클라이언트로부터 전달받은 이메일과 패스워드
        String email = registerRequestDTO.getEmail();
        String password = registerRequestDTO.getPassword();
        String profileName = registerRequestDTO.getProfileName();
        String profile = registerRequestDTO.getProfile();
        log.info("회원가입 요청 : Email: {}, Password: {}, ProfileName: {}, Profile: {}",email, password, profileName, profile );

        // 회원 가입 서비스 호출
        RegisterResponseDTO responseDTO = userService.register(email, password, profileName, profile);

        // 회원 가입 성공 시 200 OK와 응답 데이터 반환
        if (responseDTO != null && responseDTO.getUserId() != null) {
            log.info("회원가입 성공: {}", responseDTO);
            return ResponseEntity.ok(responseDTO);
        } else {
            // 회원 가입 실패 시 500 Internal Server Error와 에러 메시지 반환
            log.warn("회원가입 실패 : 사용자의 입력이 잘못되었습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDTO);
        }
    }
}

