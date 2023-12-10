package com.velog.velogproject.dto.request;

import lombok.Getter;

public class UserRequestDTO {

    @Getter
    public static class Login {
        private String email;
        private String password;
    }

    @Getter
    public static class Register {
        private String email;
        private String password;
        private String profileName;
        private String profile;
    }

}
