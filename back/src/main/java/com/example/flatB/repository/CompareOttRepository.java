package com.example.flatB.repository;

import com.example.flatB.domain.entity.CompareOttEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompareOttRepository extends JpaRepository<CompareOttEntity, Long> {
    CompareOttEntity save(CompareOttEntity compareOttEntity);
    List<CompareOttEntity> findAllByOttName(String ott_name);
    boolean existsByOttName(String ott_name);
}
