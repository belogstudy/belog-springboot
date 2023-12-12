package com.velog.velogproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.dto.response.UserResponseDTO;
import com.velog.velogproject.entity.CommentEntity;
import com.velog.velogproject.entity.PostEntity;
import com.velog.velogproject.mapper.PostMapper;
import com.velog.velogproject.repository.CommentRepository;
import com.velog.velogproject.repository.PostRepository;
import com.velog.velogproject.service.PostService;
import com.velog.velogproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;


    @Test
    public void 게시글생성_테스트() {
        int Count = 40;  // 생성할 게시글 수

        // 로그인 정보 생성
        UserResponseDTO.Login dto = userService.login("test@example.com", "password");

        // 10개의 포스트 생성
        for (int i = 1; i < Count; i++) {
            // PostEntity를 빌더 패턴을 활용하여 생성
            PostRequestDTO.CreatePost request = PostRequestDTO.CreatePost.builder()
                    .title("제목" + i)
                    .subTitle("부제목" + i)
                    .contents("내용" + i)
                    .url("url" + i)
                    .publicStatus(true) // 예시로 모든 포스트를 공개로 설정
                    .description("설명" + i)
                    .userId(dto.getUserId()) // 로그인 정보에서 사용자 정보 가져오기 (가정)
                    .build();

            // 생성한 포스트를 저장
            postService.createPost(request);
        }
    }

//    @Test
//    public void 댓글생성_테스트() {
//        // 로그인 정보 생성
//        UserResponseDTO.Login dto = userService.login("test@example.com", "password");
//
//        PostRequestDTO.CreateComment comment = PostRequestDTO.CreateComment.builder()
//                .postId()
//                .userId(dto.getUserId())
//                .content()
//                .depth()
//                .build();
//        postService.createComment()
//    }

    @Test
    public void 포스트검색(){
        UUID postId = UUID.fromString("532df4df-c29a-4815-92e8-be18e7ea032d");
        Optional<PostEntity> post = postRepository.findById(postId);

        log.info("포스트: {}", post.get());
    }

    @Test
    public void 댓글검색(){
        UUID postId = UUID.fromString("532df4df-c29a-4815-92e8-be18e7ea032d");
        PostEntity post = PostEntity.builder()
                .id(postId)
                .build();
        List<CommentEntity> entity = commentRepository.findByPostId(post);

        log.info("댓글 리스트 : {}", entity.toString());
    }

    @Test
    public void 게시글_가져오기(){
        UUID postId = UUID.fromString("532df4df-c29a-4815-92e8-be18e7ea032d");

        // DB에서 PostId에 해당하는 게시글과 댓글을 가져옵니다.
        Optional<PostEntity> postEntityOptional = postRepository.findById(postId);
        log.info("포스트: {}", postEntityOptional.get());

        PostEntity post = PostEntity.builder()
                .id(postId)
                .build();
        List<CommentEntity> commentEntityList = commentRepository.findByPostId(post);
        log.info("댓글 리스트 : {}", commentEntityList.toString());


        // DB에서 가져온 PostEntity를 PostResponseDTO.Post로 변환하여 반환합니다.
        PostEntity postEntity = postEntityOptional.get();

        log.info("게시글 DTO: {}",PostMapper.toDTO(postEntity, commentEntityList));

    }
}
