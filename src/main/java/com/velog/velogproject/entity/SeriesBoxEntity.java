package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "seriesBox")
public class SeriesBoxEntity {
    @Id
    private Long Id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private UserInfoEntity userId;
    @OneToMany(mappedBy = "seriesBox",cascade = CascadeType.ALL)
    private List<PostEntity> posts;
}
