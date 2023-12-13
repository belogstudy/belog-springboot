package com.velog.velogproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class PostRequestDTO {

    @Getter
    @Builder
    public static class CreatePost {
        private String title;
        private String subTitle;
        private String contents;
        private String url;
        private boolean publicStatus;
        private String description;
        private UUID userId;
    }
    @Getter
    public static class UpdatePost {
        private UUID postId;
        private String title;
        private String contents;
        private boolean publicStatus;
        private UUID userId;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class DeletePost {
        private UUID postId;
        private UUID userId;
    }

    @Getter
    @Builder
    public static class CreateComment{
        private UUID userId;
        private UUID postId;
        private String content;
        private int depth;
    }
    @Getter
    public static class UpdateComment{
        private UUID userId;
        private UUID postId;
        private String content;
        private int depth;
    }

    @Getter
    @Builder
    public static class DeleteComment{
        private UUID userId;
        private UUID commentId;
    }
}
