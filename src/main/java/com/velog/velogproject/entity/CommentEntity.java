package com.velog.velogproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor @Getter
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @NotNull @Lob
    private String content;
    @NotNull
    private int depth;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    private PostEntity postId;
    @ManyToOne
    private UserInfoEntity userId;

    @Builder(builderClassName = "CreateBuilder", builderMethodName = "createBuilder")
    public CommentEntity(String content, int depth, PostEntity postId, UserInfoEntity userId) {
        this.content = content;
        this.depth = depth;
        this.postId = postId;
        this.userId = userId;
    }
    @Builder(builderClassName = "UpdateBuilder", builderMethodName = "updateBuilder")
    public CommentEntity(String content, int depth, LocalDateTime updatedAt) {
        this.content = content;
        this.depth = depth;
        this.updatedAt = updatedAt;
    }
    @Builder(builderClassName = "DeleteBuilder", builderMethodName = "deleteBuilder")
    public CommentEntity(UUID id) {
        this.id = id;
    }

    public UUID getPostId() {
        return this.postId.getId();
    }

    public UUID getUserId() {
        return this.userId.getUserId();
    }
}
