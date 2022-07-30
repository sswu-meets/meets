package com.sswu.meets.controller;

import com.sswu.meets.dto.ScheduleDateTuneSaveRequestDto;
import com.sswu.meets.service.ScheduleDateTuneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RequiredArgsConstructor
@RestController
public class ScheduleDateTuneController {
    private final ScheduleDateTuneService scheduleDateTuneService;

    // 일정 날짜 조율 등록
   @PostMapping("/scheduleDateTune/{scheduleNo}")
    public Long saveDateTune(@PathVariable Long scheduleNo, @RequestBody ScheduleDateTuneSaveRequestDto requestDto) {
        return scheduleDateTuneService.saveDateTune(scheduleNo, requestDto);
    }
}
