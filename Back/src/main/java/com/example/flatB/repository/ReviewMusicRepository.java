package com.example.flatB.repository;

import com.example.flatB.domain.entity.Member;
import com.example.flatB.domain.entity.ReviewMusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewMusicRepository extends JpaRepository<ReviewMusicEntity, Long> {
    Optional<ReviewMusicEntity> findByMusicBoardno(Long music_boardno);
    List<ReviewMusicEntity> findAllByMusicPlatformnameOrderByMusicRegdateDesc(String music_platformname); //플랫폼별 최신순 조회
    List<ReviewMusicEntity> findAllByMusicPlatformnameOrderByMusicTotalpointsDesc(String music_platformname); //플랫폼별 별점순 조회
    List<ReviewMusicEntity> findAllByMemberOrderByMusicBoardnoDesc(Member member); //작성자가 쓴 게시글만 조회
}
