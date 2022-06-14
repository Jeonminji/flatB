package com.example.flatB.repository;

import com.example.flatB.domain.entity.ReviewOttEntity;
import com.example.flatB.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewOttRepository extends JpaRepository<ReviewOttEntity, Long> {
    Optional<ReviewOttEntity> findByOttBoardno(Long ott_boardno);
    List<ReviewOttEntity> findAllByOttPlatformnameOrderByOttRegdateDesc(String ott_platformname); //플랫폼별 최신순 조회
    List<ReviewOttEntity> findAllByOttPlatformnameOrderByOttTotalpointsDesc(String ott_platformname); //플랫폼별 별점순 조회
    List<ReviewOttEntity> findAllByUserEntityOrderByOttBoardnoDesc(UserEntity userEntity); //작성자가 쓴 게시글만 조회

}
