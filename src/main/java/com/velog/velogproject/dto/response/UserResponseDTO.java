package com.velog.velogproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserResponseDTO {

    public static class Info {
        private UUID userId;
        private String email;
        private String profile;
        private String profileImage;
        private String profileName;
//        private String facebook;
//        private String home;
//        private String twitter;
//        private String github;
//        private String velogTitle;
//        private String aboutMe;
//        private String snsMail;
//        private String likePosts;
    }

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
