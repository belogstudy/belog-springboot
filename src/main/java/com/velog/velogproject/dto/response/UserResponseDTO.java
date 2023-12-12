package com.velog.velogproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class UserResponseDTO {

    @Getter
    @Setter
    public static class Login {
        private UUID userId;
        private String Message;
    }

    @Getter
    @Setter
    public static class Register {

        private UUID userId;
        private String email;

        private String Message;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Withdraw {

        private UUID userId;
        private String Message;
    }
}
