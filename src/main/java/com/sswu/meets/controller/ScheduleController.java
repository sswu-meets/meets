package com.sswu.meets.controller;

import com.sswu.meets.config.auth.LoginUser;
import com.sswu.meets.config.auth.dto.SessionUser;
import com.sswu.meets.dto.*;
import com.sswu.meets.dto.schedule.request.FixScheduleSaveRequestDto;
import com.sswu.meets.dto.schedule.request.ScheduleUpdateRequestDto;
import com.sswu.meets.dto.schedule.request.TuneRangeScheduleSaveRequestDto;
import com.sswu.meets.dto.schedule.response.ScheduleResponseDto;
import com.sswu.meets.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "일정")
@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiOperation(value = "고정 일정 등록")
    @PostMapping("/fix-date")
    public Long saveFixDate(@LoginUser SessionUser sessionUser, @RequestBody FixScheduleSaveRequestDto fixRequestDto) {
        return scheduleService.saveFixDate(sessionUser, fixRequestDto);
    }

    @ApiOperation(value = "조율 일정(기간) 등록")
    @PostMapping("/tune-range")
    public Long saveTuneRangeSchedule(@LoginUser SessionUser sessionUser, @RequestBody TuneRangeScheduleSaveRequestDto tuneRequestDto) {
        return scheduleService.saveTuneRangeSchedule(sessionUser, tuneRequestDto);
    }

    @ApiOperation(value = "일정에 참여하는 유저 조회")
    @ApiImplicitParam(name = "scheduleNo", value = "일정 번호")
    @GetMapping("/userlist/{scheduleNo}")
    public List<UserResponseDto> getUserListOfSchedule(@PathVariable Long scheduleNo) {
        return scheduleService.getUserListByScheduleNo(scheduleNo);
    }

    @ApiOperation(value = "모임 일정 조회")
    @ApiImplicitParam(name = "meetingNo", value = "모임 번호")
    @GetMapping("/meeting/{meetingNo}")
    public List<ScheduleResponseDto> getScheduleList(@PathVariable Long meetingNo) {
        return scheduleService.getScheduleList(meetingNo);
    }

    @ApiOperation(value = "일정 상세 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scheduleNo", value = "일정 번호"),
            @ApiImplicitParam(name = "scheduleCode", value = "일정 코드")
    })
    @GetMapping("/{scheduleNo}/{scheduleCode}")
    public ScheduleResponseDto getSchedule(@PathVariable Long scheduleNo, @PathVariable String scheduleCode) {
        return scheduleService.getSchedule(scheduleNo, scheduleCode);
    }

    @ApiOperation(value = "일정 수정", notes = "현재는 이름만 수정 가능 (todo도 수정 가능하도록 업데이트 예정)")
    @ApiImplicitParam(name = "scheduleNo", value = "일정 번호")
    @PutMapping("/{scheduleNo}")
    public Boolean update(@PathVariable Long scheduleNo, @RequestBody ScheduleUpdateRequestDto requestDto) {
        return scheduleService.update(scheduleNo, requestDto);
    }

    @ApiOperation(value = "일정 삭제")
    @DeleteMapping("/{scheduleNo}")
    public Boolean delete(@PathVariable Long scheduleNo) {
        return scheduleService.delete(scheduleNo);
    }

}

