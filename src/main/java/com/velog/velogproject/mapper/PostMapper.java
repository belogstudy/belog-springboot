package com.velog.velogproject.mapper;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.dto.response.PostResponseDTO;
import com.velog.velogproject.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper mapper = Mappers.getMapper(PostMapper.class);

    // PostRequestDTO -> PostEntity 로 매핑
    PostEntity toPostEntity(PostRequestDTO dto);

    // PostEntity -> PostResponseDTO 로 매핑
    PostResponseDTO toPostResponseDto(PostEntity entity);

}
