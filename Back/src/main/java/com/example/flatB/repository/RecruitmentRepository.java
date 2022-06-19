package com.example.flatB.repository;

import com.example.flatB.domain.entity.Member;
import com.example.flatB.domain.entity.OttEntity;
import com.example.flatB.domain.entity.RecruitmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitmentRepository extends JpaRepository<RecruitmentEntity, Long> {
    Optional<RecruitmentEntity> findByBoardNo(Long boardNo);

    List<RecruitmentEntity> findAllByOttEntityOrderByRegdateDesc(OttEntity ottEntity); //플랫폼별
    List<RecruitmentEntity> findAllByMemberOrderByBoardNoDesc(Member member); //내 글 보기

    //모집중필터
    @Query("select r from RecruitmentEntity r where totalcount>currentcount and platformname=?1")
    List<RecruitmentEntity> findAllByOttEntityAndTotalcountEqualsCurrentcount(OttEntity ottEntity);

    //전체 글 모집중필터
    @Query("select r from RecruitmentEntity r where totalcount>currentcount")
    List<RecruitmentEntity> findAllByTotalcountEqualsCurrentcount();

    List<RecruitmentEntity> findAll(); // 전체 리뷰 조회
}
