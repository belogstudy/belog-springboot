package com.velog.velogproject.service;

import com.velog.velogproject.dto.response.CommentResponseDTO;
import com.velog.velogproject.entity.CommentEntity;
import com.velog.velogproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    // 작성중
    @Override
    public List<CommentResponseDTO> commentRetrieval(Long postId) {
        // 독립적으로 사용되는게 아닌 게시글 조회요청에 추가로 들어감.
        List<CommentEntity> commentEntityList = commentRepository.findByPostId(postId);

        // (작성중) commentEntityList 를 DTO로 매핑
        List<CommentResponseDTO> commentResponseDTOList = null;

        // 댓글 리스트를 반환
        return commentResponseDTOList;
    }

    // 작성중
    @Override
    public void commentAddition(Long postId, String content, int depth) {
        // 사용자가 댓글 작성 버튼을 눌렀을때 호출
    }

    // 작성중
    @Override
    public void commentDeletion() {
        // 사용자가 삭제 버튼을 눌렀을때 호출되며,
        // 삭제 버튼을 누른 사용자가 권한이 있는지 확인해야함.
    }

    // 작성중
    @Override
    public void commentModification() {
        // 사용자가 수정 버튼을 눌렀을떄 호출 되며,
        // 사용자에게 권한이 있는지, 변경사항이 있는지 확인해야함.
    }
}
