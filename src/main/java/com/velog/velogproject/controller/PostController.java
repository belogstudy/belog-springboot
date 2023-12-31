package com.velog.velogproject.controller;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.dto.response.PostResponseDTO;
import com.velog.velogproject.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 게시클(포스트) 관련 RestAPI Controller 구현
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "게시글 관련 API")
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Operation(summary = "전체 게시글 범위 조회 - 최신순", description = "범위(offset, limit)를 받아 해당 범위에 속한 게시글 리스트를 조회합니다.<br>" +
            "offset => index 번호 (시작 번호)<br>" +
            "limit => 게시글 갯수")
    @GetMapping("range/{offset}/{limit}")
    public ResponseEntity<List<PostResponseDTO.Post>> getPostRange(@PathVariable int offset, @PathVariable int limit){
        return ResponseEntity.ok().body(
                postService.getPostRange(offset, limit)
        );
    }


    @Operation(summary = "게시글 목록 조회 - 유저아이디", description = "유저아이디를 받아 유저가 작성한 모든 게시글을 조회합니다.")
    @GetMapping("user/{userId}")
    public ResponseEntity<List<PostResponseDTO.Post>> getPostByUserId(@PathVariable UUID userId) {
        List<PostResponseDTO.Post> postsDTO = postService.getPostByUserId(userId);
        return ResponseEntity.ok().body(postsDTO);
    }

    
    @Operation(summary = "게시글 조회", description = "게시글아이디를 받아 해당 게시글을 조회합니다.")
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO.Post> getPost(@PathVariable UUID postId) {
        PostResponseDTO.Post post = postService.getPostByPostId(postId);
        return ResponseEntity.ok().body(post);
    }


    @Operation(summary = "게시글 생성", description = "게시글 정보를 받아 게시글을 생성합니다.")
    @PostMapping("")
    public ResponseEntity<PostResponseDTO.ResponsePost> createPost(@RequestBody PostRequestDTO.CreatePost postDto) {
        PostResponseDTO.ResponsePost createdPost = postService.createPost(postDto);
        return ResponseEntity.ok().body(createdPost);
    }

    @Operation(summary = "게시글 수정", description = "게시글 정보를 받아 해당 게시글을 수정합니다.")
    @PutMapping("")
    public ResponseEntity<PostResponseDTO.ResponsePost> updatePost(@RequestBody PostRequestDTO.UpdatePost postDto) {
        PostResponseDTO.ResponsePost updatedPost = postService.updatePost(postDto);
        return ResponseEntity.ok().body(updatedPost);
    }

    @Operation(summary = "게시글 삭제", description = "게시글아이디와 사용자아이디를 받아 해당 게시글을 삭제합니다.")
    @DeleteMapping("/{postId}/{userId}")
    public ResponseEntity<PostResponseDTO.ResponsePost> deletePost(@PathVariable UUID postId, @PathVariable UUID userId) {
        PostRequestDTO.DeletePost requestDeletePost = new PostRequestDTO.DeletePost(postId, userId);
        PostResponseDTO.ResponsePost responsePostDelete = postService.deletePost(requestDeletePost);
        return ResponseEntity.ok().body(responsePostDelete);
    }

    @Operation(summary = "댓글 생성", description = "댓글 정보를 받아 댓글을 생성합니다.")
    @PostMapping("/comment")
    public ResponseEntity<PostResponseDTO.ResponseComment> createComment(@RequestBody PostRequestDTO.CreateComment commentDto){
        PostResponseDTO.ResponseComment dto = postService.createComment(commentDto);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "댓글 수정", description = "댓글 정보를 받아 해당 게시글을 수정합니다.")
    @PutMapping("/comment")
    public ResponseEntity<PostResponseDTO.ResponseComment> updateComment(@RequestBody PostRequestDTO.UpdateComment commentDto){
        PostResponseDTO.ResponseComment dto = postService.updateComment(commentDto);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "댓글 삭제", description = "댓글아이디와 사용자아이디를 받아 해당 댓글을 삭제합니다.")
    @DeleteMapping("/comment/{commentId}/{userId}")
    public ResponseEntity<PostResponseDTO.ResponseComment> deleteComment(@PathVariable UUID commentId, @PathVariable UUID userId) {

        PostResponseDTO.ResponseComment dto = postService.deleteComment(PostRequestDTO.DeleteComment.builder()
                .commentId(commentId)
                .userId(userId)
                .build()
        );

        return ResponseEntity.ok().body(dto);
    }
}
