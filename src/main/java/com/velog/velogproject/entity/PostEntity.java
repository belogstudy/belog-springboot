package com.velog.velogproject.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Velog의 Post 테이블을 매핑하는 Entity 클래스 입니다.
 * */
@Entity
@NoArgsConstructor @Getter
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @NotNull
    private String title;
    @NotNull
    private String subTitle;
    @NotNull @Lob
    private String contents;
    @NotNull
    private String url;
    @NotNull
    private Boolean publicStatus;
    @NotNull
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private int likes = 0;

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
    public PostEntity(String title, String subTitle, String contents, String url, Boolean publicStatus, String description, UserInfoEntity userId) {
        this.title = title;
        this.subTitle = subTitle;
        this.contents = contents;
        this.url = url;
        this.publicStatus = publicStatus;
        this.description = description;
        this.userId = userId;
    }

    @Builder(builderClassName = "UpdateBuilder", builderMethodName = "updateBuilder")
    public PostEntity(UUID id, String title, String contents, Boolean publicStatus, LocalDateTime updatedAt, UserInfoEntity userId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.publicStatus = publicStatus;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    @Builder(builderClassName = "DeleteBuilder", builderMethodName = "deleteBuilder")
    public PostEntity(UUID id) {
        this.id = id;
    }
}