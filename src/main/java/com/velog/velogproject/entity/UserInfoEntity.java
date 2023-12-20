package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Velog의 userInfo 테이블을 매핑하는 Entitiy 클래스 입니다.
 * */
@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "userInfo", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserInfoEntity {

    // 사용자 계정
    @Getter
    @Id
    @GeneratedValue
    private UUID userId = UUID.randomUUID();  // 생성될 때, 랜덤으로 UUID를 할당.
    @Column(unique = true)
    private String email;
    private String password;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private LocalDateTime deletedAt;
    private String profile;
    private String profileImage;
    private String profileName;
    private String facebook;
    private String home;
    private String twitter;
    private String github;
    private String velogTitle;
    private String aboutMe;
    private String snsMail;
    private String likePosts;

    @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL)
    private List<CommentEntity> comment;

    @Builder(builderClassName = "CreateBuilder", builderMethodName = "createBuilder")
    public UserInfoEntity(String email, String password, String profileName, String profile) {
        this.email = email;
        this.password = password;
        this.profileName = profileName;
        this.profile = profile;
    }

    @Builder(builderClassName = "UpdateBuilder", builderMethodName = "updateBuilder")
    public UserInfoEntity(String profile, String profileImage, String profileName,
                          String facebook, String home, String twitter, String github,
                          String velogTitle, String aboutMe, String snsMail, String likePosts) {
        this.profile = profile;
        this.profileImage = profileImage;
        this.profileName = profileName;
        this.facebook = facebook;
        this.home = home;
        this.twitter = twitter;
        this.github = github;
        this.velogTitle = velogTitle;
        this.aboutMe = aboutMe;
        this.snsMail = snsMail;
        this.likePosts = likePosts;
    }

    @Builder(builderClassName = "DeleteBuilder", builderMethodName = "deleteBuilder")
    public UserInfoEntity(UUID userId, LocalDateTime deletedAt) {
        this.userId = userId;
        this.deletedAt = deletedAt;
    }

    public UserInfoEntity(UUID userId) {
        this.userId = userId;
    }

    public UUID getId() {
        return this.userId;
    }


}