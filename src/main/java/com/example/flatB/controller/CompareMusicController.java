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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/compareMusic")
@RequiredArgsConstructor
public class CompareMusicController {
    private final CompareMusicService compareMusicService;

    @GetMapping("/melon")
    public ResponseEntity getMelon() {
        List<CompareMusicEntity> musicList = compareMusicService.getMelon();
        if (musicList.isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.CRAWLLING_FAIL),
                    HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CRAWLLING_SUCCESS, musicList),
                    HttpStatus.OK);
    }

    @GetMapping("/genie")
    public ResponseEntity getGenie() {
        List<CompareMusicEntity> musicList = compareMusicService.getGenie();
        if (musicList.isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.CRAWLLING_FAIL),
                    HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CRAWLLING_SUCCESS, musicList),
                    HttpStatus.OK);
    }

    @GetMapping("/spotify")
    public ResponseEntity getSpotify() {
        List<CompareMusicEntity> musicList = compareMusicService.getSpotify();
        if (musicList.isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.CRAWLLING_FAIL),
                    HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CRAWLLING_SUCCESS, musicList),
                    HttpStatus.OK);
    }

    @GetMapping("/bugs")
    public ResponseEntity getBugs() {
        List<CompareMusicEntity> musicList = compareMusicService.getBugs();
        if (musicList.isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.CRAWLLING_FAIL),
                    HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CRAWLLING_SUCCESS, musicList),
                    HttpStatus.OK);
    }
}
