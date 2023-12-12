package com.velog.velogproject;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.dto.response.UserResponseDTO;
import com.velog.velogproject.service.PostService;
import com.velog.velogproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostServiceTest {

    @Autowired
    private PostService postService;
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
}
