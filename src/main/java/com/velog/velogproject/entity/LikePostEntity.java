package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "likePost")
public class LikePostEntity {
    @Id
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    @ManyToOne
    private PostEntity postId;
    @OneToOne
    private UserInfoEntity userId;
}
