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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String src;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private PostEntity postId;
}
