package com.velog.velogproject.dto.request;

import com.velog.velogproject.entity.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostRequestDTO {

    @Getter
    public static class Create {
        private String title;
        private String contents;
        private Long author;
        private boolean publicStatus;
    }

    @Getter
    public static class Update {
        private Long postId;
        private String title;
        private String contents;
        private boolean publicStatus;
        private Long userId;
    }
    @Getter
    @AllArgsConstructor
    public static class Delete {
        private Long userId;
        private Long postId;
    }
}
