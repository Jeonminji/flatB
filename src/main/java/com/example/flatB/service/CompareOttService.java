package com.example.flatB.service;

import com.example.flatB.component.JsoupComponent;
import com.example.flatB.domain.dto.CompareOttDto;
import com.example.flatB.domain.entity.CompareOttEntity;
import com.example.flatB.repository.CompareOttRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CompareOttService {
    private final JsoupComponent jsoupComponent;
    private final CompareOttRepository compareOttRepository;

    public List<CompareOttEntity> getNetflix() {
        jsoupComponent.getNetflix();
        List<CompareOttEntity> compareOttEntities = compareOttRepository.findAllByOttName("Netflix");
        return compareOttEntities;
    }

    public List<CompareOttEntity> getWavve() {
        jsoupComponent.getWavve();
        List<CompareOttEntity> compareOttEntities = compareOttRepository.findAllByOttName("Wavve");
        return compareOttEntities;
    }

}
