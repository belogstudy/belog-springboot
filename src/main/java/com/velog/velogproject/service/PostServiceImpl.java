package com.velog.velogproject.service;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.dto.response.PostResponseDTO;
import com.velog.velogproject.entity.CommentEntity;
import com.velog.velogproject.entity.PostEntity;
import com.velog.velogproject.entity.UserInfoEntity;
import com.velog.velogproject.mapper.CommentMapper;
import com.velog.velogproject.mapper.PostMapper;
import com.velog.velogproject.repository.CommentRepository;
import com.velog.velogproject.repository.PostRepository;
import com.velog.velogproject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 게시글 서비스 구현
 * RequestDTO -> DAO -> Entity -> ResponseDTO
 */
@Service
@RequiredArgsConstructor @Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    @Override
    public List<PostResponseDTO.Post> getPostRange(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset / limit, limit);

        List<PostEntity> posts = postRepository.findByOrderByCreatedAtDesc(pageable);

        return posts.stream()
                .map(PostMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public PostResponseDTO.Post getPostByPostId(UUID postId) {
        // DB에서 PostId에 해당하는 게시글과 댓글을 가져옵니다.
        Optional<PostEntity> postEntityOptional = postRepository.findById(postId);  // 포스트 정보 가져오기
        PostEntity postEntity = postEntityOptional.get();
        
        PostEntity post = PostEntity.builder()
                .id(postId)
                .build();
        List<CommentEntity> commentEntityList = commentRepository.findByPostId(post);  // 댓글 리스트 가져오기

        UserInfoEntity author = userRepository.findByUserId(postEntity.getUserId());  // 포스트 작성자 정보 가져오기

        return PostMapper.toDTO(postEntity, commentEntityList, author);
    }

    @Override
    public List<PostResponseDTO.Post> getPostByUserId(UUID userId) {
        // DB 에서 UserId 가 일치하는 모든 게시글 리스트를 가져옵니다.
        UserInfoEntity author = userRepository.findByUserId(userId);

        // 받아온 게시글 리스트를 DTO 로 변환하여 반환합니다.
        List<PostEntity> posts = postRepository.findByUserId(author);

        return posts.stream()
                .map(PostMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<PostResponseDTO.Post> getPostByTitle(String title) {
        // DB 에서 SearchString 이 Title 에 포함된 게시글 리스트를 가져옵니다.
        // postRepository.findById(title);
        // 받아온 게시글 리스트를 DTO 로 변환하여 반환합니다.
        return null;
    }

    @Override
    public PostResponseDTO.ResponsePost createPost(PostRequestDTO.CreatePost postRequestDTO) {

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
        return new PostResponseDTO.ResponsePost(savePost.getId(), "게시글이 성공적으로 생성되었습니다.");
    }

    @Override
    public PostResponseDTO.ResponsePost updatePost(PostRequestDTO.UpdatePost postRequestDTO) {

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
        return new PostResponseDTO.ResponsePost(savePost.getId(), "게시글이 성공적으로 업데이트 되었습니다.");
    }

    @Override
    public PostResponseDTO.ResponsePost deletePost(PostRequestDTO.DeletePost postRequestDTO) {
        // 게시글 존재 여부 확인
        UUID postId = postRequestDTO.getPostId();
        Optional<PostEntity> existingPost = postRepository.findById(postId);

        existingPost.orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다. postId: " + postId));

        // 권한이 있는지 확인합니다.
        // TODO: 권한 확인 로직을 추가할 부분

        // 해당 게시글을 삭제처리합니다.
        postRepository.delete(existingPost.get());

        return new PostResponseDTO.ResponsePost(postId, "게시글이 성공적으로 삭제처리 되었습니다.");
    }

    @Override
    public PostResponseDTO.ResponseComment createComment(PostRequestDTO.CreateComment commentRequest) {

        CommentEntity dao = CommentMapper.toEntity(commentRequest);

        CommentEntity savedEntity = commentRepository.save(dao);

        return new PostResponseDTO.ResponseComment(savedEntity.getId(),"댓글이 성공적으로 생성 되었습니다.");
    }

    @Override
    public PostResponseDTO.ResponseComment updateComment(PostRequestDTO.UpdateComment commentRequest) {

        CommentEntity dao = CommentMapper.toEntity(commentRequest);

        CommentEntity savedEntity = commentRepository.save(dao);

        return new PostResponseDTO.ResponseComment(savedEntity.getId(),"댓글이 성공적으로 업데이트 되었습니다.");
    }

    @Override
    public PostResponseDTO.ResponseComment deleteComment(PostRequestDTO.DeleteComment commentRequest) {

        CommentEntity dao = CommentMapper.toEntity(commentRequest);

        commentRepository.delete(dao);

        return new PostResponseDTO.ResponseComment(commentRequest.getCommentId(),"댓글이 성공적으로 삭제 되었습니다.");
    }
}
