package com.example.flatB.repository;

import com.example.flatB.domain.entity.RecruitmentEntity;
import com.example.flatB.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitmentRepository extends JpaRepository<RecruitmentEntity, Long> {
    Optional<RecruitmentEntity> findByBoardNo(Long boardNo);

    List<RecruitmentEntity> findAllByPlatformnameOrderByRegdateDesc(String ott_platformname); //플랫폼별
    List<RecruitmentEntity> findAllByUserEntityOrderByBoardNoDesc(UserEntity userEntity); //내 글 보기

    //모집중필터
    @Query("select r from RecruitmentEntity r where totalcount=currentcount and platformname=?1")
    List<RecruitmentEntity>
    findAllByPlatformnameAndTotalcountEqualsCurrentcount(String ott_platfromname);
}
