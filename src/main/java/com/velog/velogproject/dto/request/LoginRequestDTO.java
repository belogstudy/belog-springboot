package com.velog.velogproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
public class LoginRequestDTO {
    private String email;
    private String password;
}
