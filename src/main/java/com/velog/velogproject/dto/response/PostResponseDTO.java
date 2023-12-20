package com.velog.velogproject.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
public class PostResponseDTO {
    @Getter @ToString
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
        private List<Comment> comments;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private int likes;

        private UUID userId;

        private UserResponseDTO.Info author;
    }

    @Getter
    @Builder
    public static class Comment {
        private UUID commentId;

        private String content;
        private int depth;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private UUID postId;
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
