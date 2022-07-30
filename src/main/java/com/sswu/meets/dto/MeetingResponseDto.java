package com.sswu.meets.dto;

import com.sswu.meets.domain.meeting.Meeting;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MeetingResponseDto {
    private Long meeting_no;
    private String name;
    private String meetingCode;
    private String meetingColor;

    public MeetingResponseDto(Meeting meetingEntity) {
        this.meeting_no = meetingEntity.getMeeting_no();
        this.name = meetingEntity.getName();
        this.meetingCode = meetingEntity.getMeetingCode();
        this.meetingColor = meetingEntity.getMeetingColor();
    }
}
