package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "photo")
public class PhotoEntity {
    @Id
    private Long id;

    private String src;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private PostEntity postId;
}
