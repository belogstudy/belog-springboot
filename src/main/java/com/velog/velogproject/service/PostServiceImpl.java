package com.velog.velogproject.service;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.dto.response.PostResponseDTO;
import com.velog.velogproject.entity.PostEntity;
import com.velog.velogproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public PostResponseDTO getPostByPostId(Long postId) {
        // DB 에서 PostId 가 일치하는 모든 게시글 리스트를 가져옵니다.
        postRepository.findById(postId);
        // 받아온 게시글 리스트를 DTO 로 변환하여 반환합니다.
        return null;
    }

    @Override
    public PostResponseDTO getPostByUserId(Long userId) {
        // DB 에서 UserId 가 일치하는 모든 게시글 리스트를 가져옵니다.


        // 받아온 게시글 리스트를 DTO 로 변환하여 반환합니다.
        List<PostEntity> posts = postRepository.findByUserId(userId);
        return null;
    }


    @Override
    public PostResponseDTO getPostByTitle() {
        // DB 에서 SearchString 이 Title 에 포함된 게시글 리스트를 가져옵니다.

        // 받아온 게시글 리스트를 DTO 로 변환하여 반환합니다.
        return null;
    }

    @Override
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO) {
        // 사용자에게 받은 PostRequstDTO를 PostEntity로 변환하여 DB에 저장합니다.
        return null;
    }

    @Override
    public PostResponseDTO updatePost(PostRequestDTO postRequestDTO) {
        // 해당 게시글 DTO를 받아서 게시글을 업데이트 합니다.
        return null;
    }

    @Override
    public PostResponseDTO deletePost(Long postId) {
        // 해당 게시글을 삭제합니다.
        // 권한이 있는지 확인해야합니다.
        return null;
    }
}
