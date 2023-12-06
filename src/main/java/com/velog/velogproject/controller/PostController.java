package com.velog.velogproject.controller;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.dto.response.PostResponseDTO;
import com.velog.velogproject.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 게시클(포스트) 관련 RestAPI Controller 구현
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    /**
     * 게시글 조회
     * @설명 postId에 해당하는 게시글 조회 로직을 수행
     * @param postId
     * @return PostResponseDTO
     */
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long postId) {
        try {
            // postId에 해당하는 게시글 조회 로직을 수행
            PostResponseDTO post = postService.getPostByPostId(postId);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            // NotFoundException이 발생하면 404 응답 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 게시글 생성
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postDto) {
        // 받아온 postDto를 이용하여 게시글 생성 로직을 수행
        PostResponseDTO createdPost = postService.createPost(postDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> updatePost(@RequestBody PostRequestDTO postDto) {
        try {
            // postId에 해당하는 게시글을 postDto 정보로 업데이트하는 로직 수행
            PostResponseDTO updatedPost = postService.updatePost(postDto);
            return ResponseEntity.ok(updatedPost);
        } catch (Exception e) {
            // NotFoundException이 발생하면 404 응답 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> deletePost(@PathVariable Long postId) {
        try {
            // postId에 해당하는 게시글을 삭제하는 로직 수행
            postService.deletePost(postId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // NotFoundException이 발생하면 404 응답 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
