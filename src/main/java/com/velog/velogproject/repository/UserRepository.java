package com.velog.velogproject.repository;

import com.velog.velogproject.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserInfoEntity, UUID> {

    UserInfoEntity findByUserId(UUID userid);
    UserInfoEntity findByEmailAndPassword(String email, String password);
}
