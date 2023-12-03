package com.velog.velogproject.service;

import com.velog.velogproject.dto.response.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    /**
     * 댓글 조회 서비스 - 게시글에 대한 댓글을 조회
     *
     * @param postId 게시글 아이디
     * @return
     */
    List<CommentResponseDTO> commentRetrieval(Long postId);

    /**
     * 댓글 작성(생성) 서비스
     * @param postId 게시글 아이디
     */
    void commentAddition(Long postId, String content, int depth);

    /**
     * 댓글 삭제 서비스
     */
    void commentDeletion();

    /**
     * 댓글 수정 서비스
     */
    void commentModification();
}
