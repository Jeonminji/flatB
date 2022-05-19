package com.example.flatB.service;

import com.example.flatB.domain.dto.ReportDto;
import com.example.flatB.domain.entity.ReportEntity;
import com.example.flatB.domain.entity.UserEntity;
import com.example.flatB.repository.ReportRepository;
import com.example.flatB.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    //게시글 생성
    @Transactional
    public Integer save(ReportDto.Request dto, String userId) {
        //User 정보를 Dto에
        UserEntity user = userRepository.getByUserId(userId);
        dto.setUser(user);
        log.info("ReportService save() Run");
        ReportEntity reports = dto.toEntity();
        reportRepository.save(reports);

        return reports.getBoardNo();
    }

}
