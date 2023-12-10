package com.velog.velogproject.dto.request;

import lombok.Getter;

@Getter
public class RegisterRequestDTO {
    private String email;
    private String password;
    private String profileName;
    private String profile;
}
