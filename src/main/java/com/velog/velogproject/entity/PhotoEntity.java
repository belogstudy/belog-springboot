package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "photo")
public class PhotoEntity {
    @Id
    @GeneratedValue
    private UUID Id = UUID.randomUUID();

    private String src;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private PostEntity postId;
}
