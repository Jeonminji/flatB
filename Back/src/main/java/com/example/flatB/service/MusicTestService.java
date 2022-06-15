package com.example.flatB.service;

import com.example.flatB.domain.entity.MusicEntity;
import com.example.flatB.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MusicTestService {
    private final MusicRepository musicRepository;

    @Transactional
    public MusicEntity getPlatform(String music_name) {
        MusicEntity musicEntity = musicRepository.findByMusicName(music_name)
                .orElseThrow(() -> new IllegalAccessError(music_name + ": 해당 플랫폼이 존재하지 않습니다."));
        return musicEntity;
    }
}
