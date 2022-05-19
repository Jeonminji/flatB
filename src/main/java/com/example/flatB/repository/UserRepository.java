package com.example.flatB.repository;

import com.example.flatB.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity save(UserEntity userEntity);

    //Security
    Optional<UserEntity> findByUserId(String userId);

    //중복 검사
    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);

    //아이디 가져오기
    UserEntity getByUserId(String userId);
}
