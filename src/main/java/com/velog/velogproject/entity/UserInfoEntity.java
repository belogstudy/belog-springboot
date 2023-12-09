package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Velog의 userInfo 테이블을 매핑하는 Entitiy 클래스 입니다.
 * */
@Entity
@NoArgsConstructor @Getter
@Table(name = "userInfo", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserInfoEntity {

    // 사용자 계정
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
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

    @Builder(builderClassName = "CreateBuilder", builderMethodName = "createBuilder")
    public UserInfoEntity(String email, String password, String profileName, String profile, LocalDateTime createAt) {
        this.email = email;
        this.password = password;
        this.profileName = profileName;
        this.profile = profile;
        this.createAt = createAt;
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

    public UserInfoEntity(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return this.userId;
    }


}