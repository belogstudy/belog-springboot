package com.velog.velogproject.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LoginResponseDTO {

    private UUID userId;
    private String Message;
}
