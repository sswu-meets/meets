package com.sswu.meets.controller;

import com.sswu.meets.dto.MeetingResponseDto;
import com.sswu.meets.dto.MeetingSaveRequestDto;
import com.sswu.meets.dto.UserResponseDto;
import com.sswu.meets.service.MeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"모임 관련 API"})
@RequiredArgsConstructor
@RestController
public class MeetingController {
    private final MeetingService meetingService;

    @ApiOperation(value = "모임에 참여하고 있는 유저 조회")
    @ApiImplicitParam(name = "meeting_no", value = "모임 아이디(고유 식별 번호)", required = true)
    @GetMapping("/meeting/{meeting_no}")
    public List<UserResponseDto> getUserListOfMeeting(@PathVariable Long meeting_no) {
        return meetingService.getUserListOfMeeting(meeting_no);
    }

    // 모임 등록
    @ApiOperation(value = "모임 등록")
    @PostMapping("/meeting/{user_no}")
    @ApiImplicitParam(name = "user_no", value = "유저 아이디(고유 식별 번호)", required = true)
    public Long saveMeeting(@PathVariable Long user_no, @RequestBody MeetingSaveRequestDto requestDto) {
        return meetingService.saveMeeting(user_no, requestDto);
    }

    // 모임 참여
    @ApiOperation(value = "모임 참여")
    @PostMapping("/meeting/{user_no}/{meetingCode}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_no", value = "유저 아이디(고유 식별 번호)", required = true),
            @ApiImplicitParam(name = "meetingCode", value = "미팅 코드", required = true)
    })
    public MeetingResponseDto participateMeeting(@PathVariable Long user_no, @PathVariable String meetingCode) {
        return meetingService.participateMeeting(user_no, meetingCode);
    }

    // 모임 나가기
    @ApiOperation(value = "모임 나가기")
    @DeleteMapping("/meeting/{user_no}/{meetingCode}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_no", value = "유저 아이디(고유 식별 번호)", required = true),
            @ApiImplicitParam(name = "meetingCode", value = "미팅 코드", required = true)
    })
    public boolean leaveMeeting(@PathVariable Long user_no, @PathVariable String meetingCode) {
        return meetingService.leaveMeeting(user_no, meetingCode);
    }
}
