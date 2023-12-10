package com.velog.velogproject.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RegisterResponseDTO {

    private UUID userId;
    private String email;

    private String Message;
}