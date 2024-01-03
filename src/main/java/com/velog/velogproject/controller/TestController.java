package com.velog.velogproject.controller;

import com.velog.velogproject.dto.response.PostResponseDTO;
import com.velog.velogproject.entity.PostEntity;
import com.velog.velogproject.repository.PostRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "테스트 API", description = "임시로 사용하는 API 입니다..")
public class TestController {

    private final PostRepository postRepository;

    @Operation(summary = "게시글 모두 조회 ", description = "게시글을 모두 조회합니다. (삭제 예정)")
    @GetMapping("/post/all")
    public ResponseEntity<List<PostResponseDTO.Post>> getPostAll() {
        List<PostEntity> postEntities = postRepository.findAll();

        List<PostResponseDTO.Post> postResponseDTOs = postEntities.stream()
                .map(postEntity -> {
                    PostResponseDTO.Post dto = PostResponseDTO.Post.builder()
                            .postId(postEntity.getId())
                            .title(postEntity.getTitle())
                            .subTitle(postEntity.getSubTitle())
                            .contents(postEntity.getContents())
                            .url(postEntity.getUrl())
                            .publicStatus(postEntity.getPublicStatus())
                            .description(postEntity.getDescription())
                            .createdAt(postEntity.getCreatedAt())
                            .updatedAt(postEntity.getUpdatedAt())
                            .likes(postEntity.getLikes())
                            .userId(postEntity.getUserId()) // 여기서 userId를 가져와서 초기화
                            .build();

                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(postResponseDTOs);
    }
}
