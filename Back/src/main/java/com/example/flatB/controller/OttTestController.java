package com.example.flatB.controller;


import com.example.flatB.common.DefaultRes;
import com.example.flatB.common.ResponseMessage;
import com.example.flatB.common.StatusCode;
import com.example.flatB.domain.dto.OttDto;
import com.example.flatB.domain.entity.OttEntity;
import com.example.flatB.service.OttTestService;
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
@RequestMapping(value = "/ottTest")
@RequiredArgsConstructor
public class OttTestController {
    private final OttTestService ottTestService;

    @GetMapping("/{platformname}")
    public ResponseEntity getPlatform(@PathVariable String platformname) {
        String ott_platformname = "";
        switch (platformname) {
            case "netflix":
                ott_platformname = "넷플릭스";
                break;
            case "watcha":
                ott_platformname = "왓챠";
                break;
            case "disneyplus":
                ott_platformname = "디즈니플러스";
                break;
            case "wavve":
                ott_platformname = "웨이브";
                break;
            case "tving":
                ott_platformname = "티빙";
                break;
            case "laftel":
                ott_platformname = "라프텔";
                break;
        }
        OttEntity ottEntity = ottTestService.getPlatform(ott_platformname);
        OttDto ottDto = OttDto.ofEntity(ottEntity);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.IMAGE_SUCCESS, ottDto),
                HttpStatus.OK);
    }
}
