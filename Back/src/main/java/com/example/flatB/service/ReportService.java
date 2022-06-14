package com.example.flatB.service;

import com.example.flatB.domain.dto.ReportDto;
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

    @Transactional
    public void post(ReportDto reportDto, String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalAccessError(userId + ": 해당 사용자가 존재하지 않습니다."));
        reportDto.setUser(userEntity);
        reportRepository.save(reportDto.toEntity());
    }

}