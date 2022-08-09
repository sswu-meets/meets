package com.sswu.meets.controller;

import com.mysql.cj.Session;
import com.sswu.meets.config.auth.dto.SessionUser;
import com.sswu.meets.dto.*;
import com.sswu.meets.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags = "일정")
@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final HttpSession httpSession;

    @ApiOperation(value = "모임에 고정 일정 등록")
    @ApiImplicitParam(name = "meetingNo", value = "모임 번호")
    @PostMapping("/schedule/fix/{meetingNo}")
    public Long saveFixDate(@PathVariable Long meetingNo, @RequestBody FixScheduleSaveRequestDto fixRequestDto) {
        return scheduleService.saveFixDate(meetingNo, fixRequestDto);
    }

    @ApiOperation(value = "모임에 조율 일정 등록")
    @ApiImplicitParam(name = "meetingNo", value = "모임 번호")
    @PostMapping("/schedule/tune/{meetingNo}")
    public Long saveTuneDate(@PathVariable Long meetingNo, @RequestBody TuneScheduleSaveRequestDto tuneRequestDto) {
        return scheduleService.saveTuneDate(meetingNo, tuneRequestDto);
    }

    @ApiOperation(value = "모임 일정 조회")
    @ApiImplicitParam(name = "meetingNo", value = "모임 번호")
    @GetMapping("/schedule/meeting/{meetingNo}")
    public List<ScheduleResponseDto> getScheduleList(@PathVariable Long meetingNo) {
        return scheduleService.getScheduleList(meetingNo);
    }

    @ApiOperation(value = "특정 일정 조회")
    @ApiImplicitParam(name = "scheduleNo", value = "일정 번호")
    @GetMapping("/schedule/{scheduleNo}")
    public List<ScheduleResponseDto> getSchedule(@PathVariable Long scheduleNo) {
        return scheduleService.getSchedule(scheduleNo);
    }

//    @ApiOperation(value = "유저가 참여하고 있는 일정 조회")
//    @ApiImplicitParam(name = "user_no", value = "유저 번호")
//    @GetMapping("/schedule/user/{user_no}")
//    public List<ScheduleResponseDto> getScheduleListOfUser(@PathVariable Long user_no) {
//        return scheduleService.getScheduleListOfUser(user_no);
//    }

    @ApiOperation(value = "유저가 참여하고 있는 일정 조회")
    @GetMapping("/schedule/user")
    public List<ScheduleResponseDto> getScheduleListOfUser() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        return scheduleService.getScheduleListOfUser(sessionUser.getUserNo());
    }

    @ApiOperation(value = "일정 수정", notes = "현재는 이름만 수정 가능 (todo도 수정 가능하도록 업데이트 예정)")
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

