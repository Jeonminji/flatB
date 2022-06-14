package com.example.flatB.repository;

import com.example.flatB.domain.entity.OttRecEntity;
import com.example.flatB.domain.entity.ReviewOttEntity;
import com.example.flatB.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OttRecRepository extends JpaRepository<OttRecEntity, Long> {
    Optional<OttRecEntity> findByUserEntityAndReviewOttEntity(UserEntity userEntity, ReviewOttEntity reviewOttEntity);
    int countByReviewOttEntity(ReviewOttEntity reviewOttEntity);
}