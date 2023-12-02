package com.velog.velogproject.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponseDTO {

    private Long userId;
    private String email;

    private String errorMessage;
}