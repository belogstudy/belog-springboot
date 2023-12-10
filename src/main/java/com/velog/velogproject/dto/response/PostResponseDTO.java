package com.velog.velogproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
public class PostResponseDTO {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Post {
        private UUID postId;

        private String title;
        private String subTitle;
        private String contents;
        private String url;
        private Boolean publicStatus;
        private String description;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private int likes;

        private UUID userId;
    }

    @Getter
    @AllArgsConstructor
    public static class ResponsePost {
        private UUID postId;
        private String message;
    }

    @Getter
    @AllArgsConstructor
    public static class ResponseComment {
        private UUID commentId;
        private String message;
    }
}
