package com.example.flatB.repository;

import com.example.flatB.domain.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicRecRepository extends JpaRepository<MusicRecEntity, Long> {
    Optional<MusicRecEntity> findByMemberAndReviewMusicEntity(Member member, ReviewMusicEntity reviewMusicEntity);
    int countByReviewMusicEntity(ReviewMusicEntity reviewMusicEntity);
}
