package com.velog.velogproject.service;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.dto.response.PostResponseDTO;
import com.velog.velogproject.entity.PostEntity;

import java.util.Optional;

/**
 * 포스트와 관련된 서비스 인터페이스
 */
public interface PostService {

    /**
     * 게시글 조회 서비스
     *
     * @getPostByPostId : 해당 게시글을 조회합니다.
     * @getPostByUserId : 사용자가 작성한 모든 게시글을 조회합니다.
     * @getPostByTitle : 사용자가 검색한 문자열이 포함되어 있는 모든 게시글을 조회합니다.
     */
    PostResponseDTO getPostByPostId(Long postId);
    PostResponseDTO getPostByUserId(Long userId);
    PostResponseDTO getPostByTitle();

    /**
     * 게시글 생성 서비스
     * @createPost : 게시글을 생성합니다.
     */
    PostResponseDTO createPost(PostRequestDTO postRequestDTO);

    /**
     * 게시글 수정 서비스
     * @updatePost : 게시글을 수정합니다.
     */
    PostResponseDTO updatePost(PostRequestDTO postRequestDTO);

    /**
     * 게시글 삭제 서비스
     * @deletePost : 게시글을 삭제합니다.
     */
    PostResponseDTO deletePost(Long postId);
}