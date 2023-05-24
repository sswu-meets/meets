package com.sswu.meets.controller;

import com.sswu.meets.dto.schedule.request.ScheduleDateTuneSaveRequestDto;
import com.sswu.meets.dto.schedule.response.ScheduleDateTuneResponseDto;
import com.sswu.meets.service.ScheduleDateTuneService;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiIgnore
@RequiredArgsConstructor
@RestController
@RequestMapping("/scheduleDateTune")
public class ScheduleDateTuneController {
    private final ScheduleDateTuneService scheduleDateTuneService;

    // 일정 날짜 조율 등록
    @PostMapping("/{scheduleNo}")
    public Long saveTuneDate(@PathVariable Long scheduleNo, @RequestBody ScheduleDateTuneSaveRequestDto requestDto) {
        return scheduleDateTuneService.saveTuneDate(scheduleNo, requestDto);
    }


    // 일정 날짜 조율 조회
    @GetMapping("/{scheduleNo}")
    public List<ScheduleDateTuneResponseDto> getDateTuneList(@PathVariable Long scheduleNo) {
        return scheduleDateTuneService.getDateTuneList(scheduleNo);
    }
}


