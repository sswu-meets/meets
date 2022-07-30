package com.sswu.meets.controller;

import com.sswu.meets.dto.ScheduleDateTuneSaveRequestDto;
import com.sswu.meets.service.ScheduleDateTuneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ScheduleDateTuneController {

//    // 일정 날짜 조율 등록
//   @PostMapping("/scheduleDateTune/{no}")
//    public Long saveDateTune(@PathVariable Long no, @RequestBody ScheduleDateTuneSaveRequestDto requestDto) {
//        return ScheduleDateTuneService.saveDateTune(no, requestDto);
//    }
}
