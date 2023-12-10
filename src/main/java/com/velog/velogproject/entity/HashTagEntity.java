package com.velog.velogproject.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "hashTag")
public class HashTagEntity {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();
    private String tagName;

    @ManyToOne
    private PostEntity postId;
}
