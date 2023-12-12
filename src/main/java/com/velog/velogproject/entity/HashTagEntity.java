package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "hashTag")
public class HashTagEntity {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();
    private String tagName;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDateTime deletedAt;

    @ManyToOne
    private PostEntity postId;
}
