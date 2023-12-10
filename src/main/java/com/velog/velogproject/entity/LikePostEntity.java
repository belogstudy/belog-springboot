package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "likePost")
public class LikePostEntity {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    @ManyToOne
    private PostEntity postId;
    @OneToOne
    private UserInfoEntity userId;
}
