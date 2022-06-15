package com.example.flatB.service;

import com.example.flatB.domain.entity.OttEntity;
import com.example.flatB.repository.OttRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OttTestService {
    private final OttRepository ottRepository;

    @Transactional
    public OttEntity getPlatform(String ott_name) {
        OttEntity ottEntity = ottRepository.findByOttName(ott_name)
                .orElseThrow(() -> new IllegalAccessError(ott_name + ": 해당 플랫폼이 존재하지 않습니다."));
        return ottEntity;
    }
}