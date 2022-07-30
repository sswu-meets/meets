package com.sswu.meets.dto;

import com.sswu.meets.domain.meeting.Meeting;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MeetingSaveRequestDto {
    private String name;
    private String meetingColor;

    @Builder
    public MeetingSaveRequestDto(String name, String meetingColor) {
        this.name = name;
        this.meetingColor = meetingColor;
    }

    public Meeting toEntity(String meetingCode) {
        return Meeting.builder()
                .name(name)
                .meetingCode(meetingCode)
                .meetingColor(meetingColor)
                .build();
    }
}
