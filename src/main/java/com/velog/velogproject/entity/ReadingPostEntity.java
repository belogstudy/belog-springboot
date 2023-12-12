package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Velog의 readingPost 테이블을 매핑하는 Entity 클래스 입니다.
 * @참고: "readingPost 테이블은 사용자가 게시글을 읽었을때, 게시글에 대한 읽었다는 기록이 저장됩니다."
 * */
@Entity
@NoArgsConstructor
@Table(name = "readingPost")
public class ReadingPostEntity {
    @Id
    @GeneratedValue
    private UUID Id = UUID.randomUUID();
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime deletedAt;
    @ManyToOne
    private PostEntity postId;
    @ManyToOne
    private UserInfoEntity userId;
}
