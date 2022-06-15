package com.example.flatB.repository;

import com.example.flatB.domain.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<MusicEntity, String> {
    Optional<MusicEntity> findByMusicName(String music_name);
}

