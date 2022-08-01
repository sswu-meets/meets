package com.sswu.meets.controller;

import com.sswu.meets.dto.ScheduleResponseDto;
import com.sswu.meets.dto.ScheduleSaveRequestDto;
import com.sswu.meets.dto.ScheduleUpdateRequestDto;
import com.sswu.meets.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "일정")
@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiOperation(value = "모임에 일정 등록")
    @ApiImplicitParam(name = "meetingNo", value = "모임 번호")
    @PostMapping("/schedule/{meetingNo}")
    public Long save(@PathVariable Long meetingNo, @RequestBody ScheduleSaveRequestDto scheduleSaveRequestDto) {
        return scheduleService.save(meetingNo, scheduleSaveRequestDto);
    }

    @ApiOperation(value = "모임 일정 조회")
    @ApiImplicitParam(name = "meetingNo", value = "모임 번호")
    @GetMapping("/schedule/{meetingNo}")
    public List<ScheduleResponseDto> getScheduleList(@PathVariable Long meetingNo) {
        return scheduleService.getScheduleList(meetingNo);
    }

    @ApiOperation(value = "일정 수정")
    @ApiImplicitParam(name = "scheduleNo", value = "일정 번호")
    @PutMapping("/schedule/{scheduleNo}")
    public Boolean update(@PathVariable Long scheduleNo, @RequestBody ScheduleUpdateRequestDto requestDto) {
        return scheduleService.update(scheduleNo, requestDto);
    }

    @ApiOperation(value = "일정 삭제")
    @DeleteMapping("/schedule/{scheduleNo}")
    public Boolean delete(@PathVariable Long scheduleNo) {
        return scheduleService.delete(scheduleNo);
    }

}

