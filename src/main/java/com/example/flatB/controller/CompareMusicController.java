package com.example.flatB.controller;

import com.example.flatB.common.DefaultRes;
import com.example.flatB.common.ResponseMessage;
import com.example.flatB.common.StatusCode;
import com.example.flatB.domain.entity.CompareMusicEntity;
import com.example.flatB.service.CompareMusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/compareMusic")
@RequiredArgsConstructor
public class CompareMusicController {
    private final CompareMusicService compareMusicService;

    @GetMapping("/{platformName}")
    public ResponseEntity getPlatform(@PathVariable String platformName) {
        List<CompareMusicEntity> musicList = new ArrayList<>();
        switch (platformName) {
            case "melon":
                musicList = compareMusicService.getMelon();
                break;
            case "genie":
                musicList = compareMusicService.getGenie();
                break;
            case "spotify":
                musicList = compareMusicService.getSpotify();
                break;
            case "bugs":
                musicList = compareMusicService.getBugs();
                break;
        }
        if (musicList.isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.CRAWLLING_FAIL),
                    HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CRAWLLING_SUCCESS, musicList),
                    HttpStatus.OK);
    }
}
