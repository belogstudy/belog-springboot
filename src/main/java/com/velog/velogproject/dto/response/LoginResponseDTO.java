package com.velog.velogproject.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private Long userId;
    private String errorMessage;
}
