package com.velog.velogproject.service;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.dto.response.PostResponseDTO;
import com.velog.velogproject.entity.PostEntity;
import com.velog.velogproject.entity.UserInfoEntity;
import com.velog.velogproject.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public PostResponseDTO.Info getPostByPostId(UUID postId) {
        // DB에서 PostId에 해당하는 글을 가져옵니다.
        Optional<PostEntity> entityOptional = postRepository.findById(postId);

        // 만약 해당 postId에 대한 글이 없다면 null을 반환하거나 예외를 처리
        // TODO: 예외 처리

        // DB에서 가져온 PostEntity를 PostResponseDTO.Info로 변환하여 반환합니다.
        PostEntity postEntity = entityOptional.get();
        PostResponseDTO.Info responseDto = PostResponseDTO.Info.builder()
                .postId(postEntity.getId())
                .title(postEntity.getTitle())
                .contents(postEntity.getContents())
                .url(postEntity.getUrl())
                .publicStatus(postEntity.getPublicStatus())
                .description(postEntity.getDescription())
                .createdAt(postEntity.getCreatedAt())
                .updatedAt(postEntity.getUpdatedAt())
                .likes(postEntity.getLikes())
                .userId(postEntity.getId())
                .build();

        return responseDto;
    }

    @Override
    public List<PostResponseDTO.Info> getPostByUserId(UUID userId) {
        // DB 에서 UserId 가 일치하는 모든 게시글 리스트를 가져옵니다.
        // 받아온 게시글 리스트를 DTO 로 변환하여 반환합니다.
        // List<PostEntity> posts = postRepository.findByUserId(userId);
        return null;
    }


    @Override
    public List<PostResponseDTO.Info> getPostByTitle(String title) {
        // DB 에서 SearchString 이 Title 에 포함된 게시글 리스트를 가져옵니다.
        // postRepository.findById(title);
        // 받아온 게시글 리스트를 DTO 로 변환하여 반환합니다.
        return null;
    }

    @Override
    public PostResponseDTO.Response createPost(PostRequestDTO.Create postRequestDTO) {

        // 사용자에게 받은 PostRequstDTO를 PostEntity로 변환하여 DB에 저장합니다.
        PostEntity dao = PostEntity.createBuilder()
                .title(postRequestDTO.getTitle())
                .subTitle(postRequestDTO.getSubTitle())
                .contents(postRequestDTO.getContents())
                .url(postRequestDTO.getUrl())
                .publicStatus(postRequestDTO.isPublicStatus())
                .description(postRequestDTO.getDescription())
                .userId(new UserInfoEntity(postRequestDTO.getUserId())) // 게시글 작성자
                .build();

        PostEntity savePost = postRepository.save(dao);

        // 게시글 생성이 성공하면 생성된 게시글아이디(postId) 를 반환합니다.
        return new PostResponseDTO.Response(savePost.getId(), "게시글이 성공적으로 생성되었습니다.");
    }

    @Override
    public PostResponseDTO.Response updatePost(PostRequestDTO.Update postRequestDTO) {

        // 게시글 존재 여부 확인
        PostEntity existingPost = postRepository.findById(postRequestDTO.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다. postId: " + postRequestDTO.getPostId()));

        // 해당 게시글 DTO를 받아서 게시글을 업데이트합니다.
        PostEntity updatePost = PostEntity.updateBuilder()
                .id(postRequestDTO.getPostId())
                .title(postRequestDTO.getTitle())
                .contents(postRequestDTO.getContents())
                .publicStatus(postRequestDTO.isPublicStatus())
                .updatedAt(LocalDateTime.now())
                .userId(new UserInfoEntity(postRequestDTO.getUserId()))
                .build();

        // 게시글 업데이트 수행
        PostEntity savePost = postRepository.save(updatePost);

        // 게시글 업데이트가 성공하면 생성된 게시글아이디(postId) 를 반환합니다.
        return new PostResponseDTO.Response(savePost.getId(), "게시글이 성공적으로 업데이트 되었습니다.");
    }

    @Override
    public PostResponseDTO.Response deletePost(PostRequestDTO.Delete requestDTO) {

        // 게시글 존재 여부 확인
        PostEntity existingPost = postRepository.findById(requestDTO.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다. postId: " + requestDTO.getPostId()));

        // 권한이 있는지 확인합니다.
        // TODO: 권한 확인 로직을 추가할 부분

        // 해당 게시글을 삭제합니다.
        PostEntity deletePost = PostEntity.deleteBuilder()
                .id(requestDTO.getPostId())
                .build();

        postRepository.delete(deletePost);

        return new PostResponseDTO.Response(requestDTO.getPostId(),"게시글이 성공적으로 삭제되었습니다.");
    }
}
