package com.velog.velogproject.dto.response;

import com.velog.velogproject.entity.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
public class PostResponseDTO {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Info{
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
    public static class Response {
        private UUID postId;
        private String message;
    }
}
