package com.sswu.meets.controller;

import com.sswu.meets.dto.ScheduleDateTuneResponseDto;
import com.sswu.meets.dto.ScheduleDateTuneSaveRequestDto;
import com.sswu.meets.dto.TodoResponseDto;
import com.sswu.meets.service.ScheduleDateTuneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScheduleDateTuneController {
    private final ScheduleDateTuneService scheduleDateTuneService;

    // 일정 날짜 조율 등록
    @PostMapping("/scheduleDateTune/{scheduleNo}")
    public Long saveDateTune(@PathVariable Long scheduleNo, @RequestBody ScheduleDateTuneSaveRequestDto requestDto) {
        return scheduleDateTuneService.saveDateTune(scheduleNo, requestDto);
    }


    // 일정 날짜 조율 조회
    @GetMapping("/scheduleDateTune/{scheduleNo}")
    public List<ScheduleDateTuneResponseDto> getDateTuneList(@PathVariable Long scheduleNo) {
        return scheduleDateTuneService.getDateTuneList(scheduleNo);
    }
}


