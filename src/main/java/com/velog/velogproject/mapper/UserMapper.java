package com.velog.velogproject.mapper;

import com.velog.velogproject.dto.response.UserResponseDTO;
import com.velog.velogproject.entity.UserInfoEntity;

public class UserMapper {

    public static UserResponseDTO.Info toDTO(UserInfoEntity user) {
        return UserResponseDTO.Info.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .profile(user.getProfile())
                .profileImage(user.getProfileImage())
                .profileName(user.getProfileName())
                .build();
    }

}
