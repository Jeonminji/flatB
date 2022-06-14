package com.example.flatB.repository;

import com.example.flatB.domain.entity.OttEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OttRepository extends JpaRepository<OttEntity, String> {
    Optional<OttEntity> findByOttName(String ott_name);
}
