package com.example.flatB.service;

import com.example.flatB.component.JsoupComponent;
import com.example.flatB.domain.entity.CompareMusicEntity;
import com.example.flatB.repository.CompareMusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompareMusicService {
    private final JsoupComponent jsoupComponent;
    private final CompareMusicRepository compareMusicRepository;

    public List<CompareMusicEntity> getMelon() {
        jsoupComponent.getMelon();
        List<CompareMusicEntity> compareMusicEntities = compareMusicRepository.findAllByMusicName("Melon");
        return compareMusicEntities;
    }

    public List<CompareMusicEntity> getGenie() {
        jsoupComponent.getGenie();
        List<CompareMusicEntity> compareMusicEntities = compareMusicRepository.findAllByMusicName("Genie");
        return compareMusicEntities;
    }

    public List<CompareMusicEntity> getSpotify() {
        jsoupComponent.getSpotify();
        List<CompareMusicEntity> compareMusicEntities = compareMusicRepository.findAllByMusicName("Spotify");
        return compareMusicEntities;
    }

    public List<CompareMusicEntity> getBugs() {
        jsoupComponent.getBugs();
        List<CompareMusicEntity> compareMusicEntities = compareMusicRepository.findAllByMusicName("Bugs");
        return compareMusicEntities;
    }
}
