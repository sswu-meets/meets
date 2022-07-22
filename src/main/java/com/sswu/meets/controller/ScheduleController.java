package com.sswu.meets.controller;

import com.sswu.meets.dto.ScheduleResponseDto;
import com.sswu.meets.dto.ScheduleSaveRequestDto;
import com.sswu.meets.dto.ScheduleUpdateRequestDto;
import com.sswu.meets.dto.UserSaveRequestDto;
import com.sswu.meets.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    //일정 등록
    @PostMapping("/schedule/{meetingNo}")
    public Long save(@PathVariable Long meetingNo, @RequestBody ScheduleSaveRequestDto scheduleSaveRequestDto) {
        return scheduleService.save(meetingNo, scheduleSaveRequestDto);
    }

    //일정 수정
    @PutMapping("/schedule/{scheduleNo}")
    public Boolean update(@PathVariable Long scheduleNo, @RequestBody ScheduleUpdateRequestDto requestDto) {
        return scheduleService.update(scheduleNo, requestDto);
    }

    //일정 조회
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getScheduleList() {
        return scheduleService.getScheduleList();
    }
}

