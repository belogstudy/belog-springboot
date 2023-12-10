package com.velog.velogproject.repository;

import com.velog.velogproject.entity.CommentEntity;
import com.velog.velogproject.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
}
