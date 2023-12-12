package com.velog.velogproject.repository;

import com.velog.velogproject.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    Optional<PostEntity> findById(UUID id);
    // List<PostEntity> findByUserId(Long userId);
}
