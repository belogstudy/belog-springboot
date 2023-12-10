package com.velog.velogproject.dto.request;

import com.velog.velogproject.entity.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class PostRequestDTO {

    @Getter
    @Builder
    public static class Create {
        private String title;
        private String subTitle;
        private String contents;
        private String url;
        private boolean publicStatus;
        private String description;
        private UUID userId;
    }
    @Getter
    public static class Update {
        private UUID postId;
        private String title;
        private String contents;
        private boolean publicStatus;
        private UUID userId;
    }
    @Getter
    @AllArgsConstructor
    public static class Delete {
        private UUID userId;
        private UUID postId;
    }
}
