package com.velog.velogproject.repository;

import com.velog.velogproject.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    // List<PostEntity> findByUserId(Long userId);
}
