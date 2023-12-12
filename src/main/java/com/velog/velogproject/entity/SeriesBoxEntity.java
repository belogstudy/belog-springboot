package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "seriesBox")
public class SeriesBoxEntity {
    @Id
    @GeneratedValue
    private UUID Id = UUID.randomUUID();
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @ManyToOne
    private UserInfoEntity userId;
    @OneToMany(mappedBy = "seriesBox",cascade = CascadeType.ALL)
    private List<PostEntity> posts;
}
