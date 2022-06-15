package com.example.flatB.controller;

import com.example.flatB.common.DefaultRes;
import com.example.flatB.common.ResponseMessage;
import com.example.flatB.common.StatusCode;
import com.example.flatB.domain.dto.MusicDto;
import com.example.flatB.domain.entity.MusicEntity;
import com.example.flatB.service.MusicTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/musicTest")
@RequiredArgsConstructor
public class MusicTestController {
    private final MusicTestService musicTestService;

    @GetMapping("/{platformname}")
    public ResponseEntity getPlatform(@PathVariable String platformname) {
        String music_platformname = "";
        switch (platformname) {
            case "melon":
                music_platformname = "멜론";
                break;
            case "genie":
                music_platformname = "지니";
                break;
            case "bugs":
                music_platformname = "벅스";
                break;
            case "youtubemusic":
                music_platformname = "유튜브뮤직";
                break;
            case "flo":
                music_platformname = "플로";
                break;
            case "spotify":
                music_platformname = "스포티파이";
                break;
        }

        MusicEntity musicEntity = musicTestService.getPlatform(music_platformname);
        MusicDto musicDto = MusicDto.ofEntity(musicEntity);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CRAWLLING_SUCCESS, musicDto),
                HttpStatus.OK);
    }
}
