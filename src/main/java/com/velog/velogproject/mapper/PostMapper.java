package com.velog.velogproject.mapper;

import com.velog.velogproject.dto.request.PostRequestDTO;
import com.velog.velogproject.entity.PostEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PostMepper {
    PostMapper INSTANCE = Mapper.getPost(PostMepper.class);

    // PostRequestDTO -> PostEntity 로 매핑
    PostEntity toPostEntity(PostRequestDTO requestdot);

}
