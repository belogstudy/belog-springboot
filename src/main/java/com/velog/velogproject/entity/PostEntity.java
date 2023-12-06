package com.velog.velogproject.entity;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Velog의 Post 테이블을 매핑하는 Entity 클래스 입니다.
 * */
@Entity
@NoArgsConstructor
@Table(name = "post")
public class PostEntity {
    @Id
    private Long Id;

    private String title;
    private String contents;
    private String url;
    private Boolean publicStatus;
    private String description;

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
}