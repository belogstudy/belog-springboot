package com.velog.velogproject.mapper;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.entity.CommentEntity;
import com.velog.velogproject.entity.PostEntity;
import com.velog.velogproject.entity.UserInfoEntity;

import java.time.LocalDateTime;

public class CommentMapper {
    public static CommentEntity toEntity(PostRequestDTO.CreateComment createCommentDTO) {
        return CommentEntity.createBuilder()
                .content(createCommentDTO.getContent())
                .depth(createCommentDTO.getDepth())
                .postId(PostEntity.builder()
                        .id(createCommentDTO.getPostId())
                        .build())
                .userId(new UserInfoEntity(createCommentDTO.getUserId()))
                .build();
    }

    public static CommentEntity toEntity(PostRequestDTO.UpdateComment updateCommentDTO) {
        return CommentEntity.updateBuilder()
                .content(updateCommentDTO.getContent())
                .depth(updateCommentDTO.getDepth())
                .updatedAt(LocalDateTime.now()) // 업데이트 시간을 현재 시간으로 설정.
                .build();
    }

    public static CommentEntity toEntity(PostRequestDTO.DeleteComment deleteCommentDTO) {
        return CommentEntity.deleteBuilder()
                .id(deleteCommentDTO.getCommentId())
                .build();
    }
}

