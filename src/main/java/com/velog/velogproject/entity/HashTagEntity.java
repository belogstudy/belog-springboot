package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "hashTag")
public class HashTagEntity {
    @Id
    private Long id;
    private String tagName;

    @ManyToOne
    private PostEntity postId;
}
