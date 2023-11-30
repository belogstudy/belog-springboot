package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Velog의 userInfo 테이블을 매핑하는 Entitiy 클래스 입니다.
 * */
@Entity
@NoArgsConstructor
@Table(name = "userInfo")
public class UserInfoEntity {

    // 사용자 계정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

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
}