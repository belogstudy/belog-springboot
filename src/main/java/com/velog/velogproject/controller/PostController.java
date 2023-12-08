package com.velog.velogproject.controller;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.dto.response.PostResponseDTO;
import com.velog.velogproject.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<PostResponseDTO.Info> getPost(@PathVariable Long postId) {

        PostResponseDTO.Info post = postService.getPostByPostId(postId);
        return ResponseEntity.ok(post);
    }

    // 게시글 생성
    @PostMapping
    public ResponseEntity<PostResponseDTO.Response> createPost(@RequestBody PostRequestDTO.Create postDto) {
        // 받아온 postDto 를 이용하여 게시글 생성 로직을 수행
        PostResponseDTO.Response createdPost = postService.createPost(postDto);
        return ResponseEntity.ok().body(createdPost);
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDTO.Response> updatePost(@RequestBody PostRequestDTO.Update postDto) {
        // postId에 해당하는 게시글을 postDto 정보로 업데이트하는 로직 수행
        PostResponseDTO.Response updatedPost = postService.updatePost(postDto);
        return ResponseEntity.ok(updatedPost);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostResponseDTO.Response> deletePost(@PathVariable Long postId, @RequestParam Long userId) {
        // postId에 해당하는 게시글을 삭제하는 로직 수행 (해당 게시글이 userId의 소유인지 확인)
        PostRequestDTO.Delete requestDelete = new PostRequestDTO.Delete(postId, userId);
        PostResponseDTO.Response responseDelete = postService.deletePost(requestDelete);

        return ResponseEntity.ok().body(responseDelete);
    }
}
