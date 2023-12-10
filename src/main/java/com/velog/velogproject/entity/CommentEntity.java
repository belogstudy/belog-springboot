package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String content;
    private int depth;
    @ManyToOne
    private PostEntity postId;
}
