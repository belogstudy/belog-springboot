package com.velog.velogproject.controller;

import com.velog.velogproject.dto.response.PostResponseDTO;
import com.velog.velogproject.entity.PostEntity;
import com.velog.velogproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TestController {

    private final PostRepository postRepository;

    @GetMapping("/post/all")
    public ResponseEntity<List<PostResponseDTO.Info>> getPostAll() {
        List<PostEntity> postEntities = postRepository.findAll();

        List<PostResponseDTO.Info> postResponseDTOs = postEntities.stream()
                .map(postEntity -> {
                    PostResponseDTO.Info dto = PostResponseDTO.Info.builder()
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
                            .userId(postEntity.getUserId().getId()) // 여기서 userId를 가져와서 초기화
                            .build();

                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(postResponseDTOs);
    }

}
