package com.velog.velogproject.service;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.dto.response.PostResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * 포스트와 관련된 서비스 인터페이스
 */
public interface PostService {

    /**
     * 게시글 조회 서비스
     * @getPostByPostId : 해당 게시글을 조회합니다.
     * @getPostByUserId : 사용자가 작성한 모든 게시글을 조회합니다.
     * @getPostByTitle : 사용자가 검색한 문자열이 포함되어 있는 모든 게시글을 조회합니다.
     */
    PostResponseDTO.Post getPostByPostId(UUID postId);
    List<PostResponseDTO.Post> getPostByUserId(UUID userId);
    List<PostResponseDTO.Post> getPostByTitle(String title);

    /**
     * 게시글 서비스
     * @createPost : 게시글을 생성합니다.
     * @updatePost : 게시글을 수정합니다.
     * @deletePost : 게시글을 삭제합니다.
     */
    PostResponseDTO.ResponsePost createPost(PostRequestDTO.CreatePost postRequestDTO);
    PostResponseDTO.ResponsePost updatePost(PostRequestDTO.UpdatePost postRequestDTO);
    PostResponseDTO.ResponsePost deletePost(PostRequestDTO.DeletePost postRequestDTO);

    /**
     * 댓글 서비스
     * @createComment : 댓글을 삭제합니다.
     * @updateComment : 댓글을 수정합니다.
     * @deleteCommnet : 댓글을 삭제합니다.
     */
    PostResponseDTO.ResponseComment createComment(PostRequestDTO.CreateComment commentRequest);
    PostResponseDTO.ResponseComment updateComment(PostRequestDTO.UpdateComment commentRequest);
    PostResponseDTO.ResponseComment deleteComment(PostRequestDTO.DeleteComment commentRequest);

}