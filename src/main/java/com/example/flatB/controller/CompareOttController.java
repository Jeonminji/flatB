package com.example.flatB.controller;

import com.example.flatB.common.DefaultRes;
import com.example.flatB.common.ResponseMessage;
import com.example.flatB.common.StatusCode;
import com.example.flatB.domain.entity.CompareOttEntity;
import com.example.flatB.service.CompareOttService;
import lombok.Builder;
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
@RequestMapping(value = "/compareOtt")
@RequiredArgsConstructor
public class CompareOttController {
    private final CompareOttService compareOttService;

    @GetMapping("/netflix")
    public ResponseEntity getNetflix() {
        List<CompareOttEntity> ottList = compareOttService.getNetflix();
        if(ottList.isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.CRAWLLING_FAIL),
                    HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CRAWLLING_SUCCESS, ottList),
                HttpStatus.OK);
    }

    @GetMapping("/wavve")
    public ResponseEntity getWavve() {
        List<CompareOttEntity> ottList = compareOttService.getWavve();
        if(ottList.isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.CRAWLLING_FAIL),
                    HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CRAWLLING_SUCCESS, ottList),
                    HttpStatus.OK);
    }
}
