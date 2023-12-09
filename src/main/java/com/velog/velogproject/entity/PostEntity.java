package com.velog.velogproject.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Velog의 Post 테이블을 매핑하는 Entity 클래스 입니다.
 * */
@Entity
@NoArgsConstructor @Getter
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;
    private String subTitle;
    private String contents;
    private String url;
    private Boolean publicStatus;
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int likes;

    @ManyToOne
    private UserInfoEntity userId;
    @ManyToOne
    private SeriesBoxEntity seriesBox;
    @OneToMany(mappedBy = "postId",cascade = CascadeType.ALL)
    private List<LikePostEntity> likePost;
    @OneToMany(mappedBy = "postId",cascade = CascadeType.ALL)
    private List<CommentEntity> comments;
    @OneToMany(mappedBy = "postId",cascade = CascadeType.ALL)
    private List<HashTagEntity> hashTags;
    @OneToMany(mappedBy = "postId",cascade = CascadeType.ALL)
    private List<PhotoEntity> photos;

    @Builder(builderClassName = "CreateBuilder", builderMethodName = "createBuilder")
    public PostEntity(String title, String contents, Boolean publicStatus, LocalDateTime createdAt, LocalDateTime updatedAt, int likes, UserInfoEntity userId) {
        this.title = title;
        this.contents = contents;
        this.publicStatus = publicStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likes = likes;
        this.userId = userId;
    }

    @Builder(builderClassName = "UpdateBuilder", builderMethodName = "updateBuilder")
    public PostEntity(Long id, String title, String contents, Boolean publicStatus, LocalDateTime updatedAt, UserInfoEntity userId) {
        Id = id;
        this.title = title;
        this.contents = contents;
        this.publicStatus = publicStatus;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    @Builder(builderClassName = "DeleteBuilder", builderMethodName = "deleteBuilder")
    public PostEntity(Long id) {
        Id = id;
    }
}