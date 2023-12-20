package com.velog.velogproject.mapper;

import com.velog.velogproject.dto.response.PostResponseDTO;
import com.velog.velogproject.entity.CommentEntity;
import com.velog.velogproject.entity.PostEntity;
import com.velog.velogproject.entity.UserInfoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public static PostResponseDTO.Post toDTO(PostEntity postEntity, List<CommentEntity> comments, UserInfoEntity author) {
        return PostResponseDTO.Post.builder()
                .postId(postEntity.getId())
                .title(postEntity.getTitle())
                .subTitle(postEntity.getSubTitle())
                .contents(postEntity.getContents())
                .url(postEntity.getUrl())
                .publicStatus(postEntity.getPublicStatus())
                .description(postEntity.getDescription())
                .comments(mapCommentEntitiesToDTO(comments))
                .createdAt(postEntity.getCreatedAt())
                .updatedAt(postEntity.getUpdatedAt())
                .likes(postEntity.getLikes())
                .userId(postEntity.getUserId())
                .author(UserMapper.toDTO(author))
                .build();
    }

    private static List<PostResponseDTO.Comment> mapCommentEntitiesToDTO(List<CommentEntity> commentEntities) {
        return commentEntities.stream()
                .map(commentEntity -> PostResponseDTO.Comment.builder()
                        .commentId(commentEntity.getId())
                        .content(commentEntity.getContent())
                        .depth(commentEntity.getDepth())
                        .createdAt(commentEntity.getCreatedAt())
                        .updatedAt(commentEntity.getUpdatedAt())
                        .postId(commentEntity.getPostId())
                        .userId(commentEntity.getUserId().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
