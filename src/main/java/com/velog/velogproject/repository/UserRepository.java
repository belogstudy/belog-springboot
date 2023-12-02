package com.velog.velogproject.repository;

import com.velog.velogproject.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfoEntity, Long> {

    UserInfoEntity findByUserId(Long userid);
    UserInfoEntity findByEmailAndPassword(String email, String password);
}
