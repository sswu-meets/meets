package com.sswu.meets.controller;

import com.sswu.meets.dto.MeetingResponseDto;
import com.sswu.meets.dto.MeetingSaveRequestDto;
import com.sswu.meets.dto.UserResponseDto;
import com.sswu.meets.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MeetingController {
    private final MeetingService meetingService;

    // 모임에 참여하고 있는 유저 조회
    @GetMapping("/meeting/{meeting_no}")
    public List<UserResponseDto> getUserListOfMeeting(@PathVariable Long meeting_no) {
        return meetingService.getUserListOfMeeting(meeting_no);
    }

    // 모임 등록
    @PostMapping("/meeting/{user_no}")
    public Long saveMeeting(@PathVariable Long user_no, @RequestBody MeetingSaveRequestDto requestDto) {
        return meetingService.saveMeeting(user_no, requestDto);
    }

    // 모임 참여
    @PostMapping("/meeting/{user_no}/{meetingCode}")
    public MeetingResponseDto participateMeeting(@PathVariable Long user_no, @PathVariable String meetingCode) {
        return meetingService.participateMeeting(user_no, meetingCode);
    }

    // 모임 나가기
    @DeleteMapping("/meeting/{user_no}/{meetingCode}")
    public boolean leaveMeeting(@PathVariable Long user_no, @PathVariable String meetingCode) {
        return meetingService.leaveMeeting(user_no, meetingCode);
    }
}
