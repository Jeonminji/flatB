package com.example.flatB.service;

import com.example.flatB.domain.dto.ReportDto;
import com.example.flatB.domain.entity.Member;
import com.example.flatB.repository.MemberRepository;
import com.example.flatB.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void post(ReportDto reportDto, String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalAccessError(userId + ": 해당 사용자가 존재하지 않습니다."));
        reportDto.setMember(member);
        reportRepository.save(reportDto.toEntity());
    }

}