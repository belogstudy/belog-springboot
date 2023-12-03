package com.velog.velogproject.repository;

import com.velog.velogproject.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    // 게시글 아이디에 대한 모든 댓글을 가져옴.
    List<CommentEntity> findByPostId(Long postId);
}
