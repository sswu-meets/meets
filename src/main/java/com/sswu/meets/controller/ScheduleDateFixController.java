package com.sswu.meets.controller;

import com.sswu.meets.dto.schedule.request.ScheduleDateFixSaveRequestDto;
import com.sswu.meets.service.ScheduleDateFixService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RequiredArgsConstructor
@RestController
@RequestMapping("/scheduleDateFix")
public class ScheduleDateFixController {
    private final ScheduleDateFixService scheduleDateFixService;

    // 일정의 고정 날짜&시간 등록
    @PostMapping("/{scheduleNo}")
    public Long saveFixDate(@PathVariable Long scheduleNo, @RequestBody ScheduleDateFixSaveRequestDto requestDto) {
        return scheduleDateFixService.saveFixDate(scheduleNo, requestDto);
    }
}
