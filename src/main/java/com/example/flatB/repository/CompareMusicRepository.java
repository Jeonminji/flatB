package com.example.flatB.repository;

import com.example.flatB.domain.entity.CompareMusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompareMusicRepository extends JpaRepository<CompareMusicEntity, Long> {
    CompareMusicEntity save(CompareMusicEntity compareMusicEntity);
    List<CompareMusicEntity> findAllByMusicName(String music_name);
    boolean existsByMusicName(String music_name);
}
