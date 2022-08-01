package com.sswu.meets.controller;

import com.sswu.meets.config.auth.dto.SessionUser;
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

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags = "모임")
@RequiredArgsConstructor
@RestController
public class MeetingController {
    private final MeetingService meetingService;
    private final HttpSession httpSession;

    @ApiOperation(value = "모임에 참여하고 있는 유저 조회")
    @ApiImplicitParam(name = "meeting_no", value = "모임 아이디(고유 식별 번호)", required = true)
    @GetMapping("/meeting/{meeting_no}")
    public List<UserResponseDto> getUserListOfMeeting(@PathVariable Long meeting_no) {
        return meetingService.getUserListOfMeeting(meeting_no);
    }

    @ApiOperation(value = "모임 등록")
    @PostMapping("/meeting")
    public Long saveMeeting(@RequestBody MeetingSaveRequestDto requestDto) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");


        return meetingService.saveMeeting(sessionUser.getUserNo(), requestDto);
    }

    @ApiOperation(value = "모임 참여")
    @PostMapping("/meeting/{user_no}/{meetingCode}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_no", value = "유저 아이디(고유 식별 번호)", required = true),
            @ApiImplicitParam(name = "meetingCode", value = "미팅 코드", required = true)
    })
    public MeetingResponseDto participateMeeting(@PathVariable Long user_no, @PathVariable String meetingCode) {
        return meetingService.participateMeeting(user_no, meetingCode);
    }

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
