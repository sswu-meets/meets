package com.sswu.meets.dto;

import com.sswu.meets.domain.meeting.Meeting;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MeetingSaveRequestDto {
    private String name;
    private String meetingCode;

    @Builder
    public MeetingSaveRequestDto(String name, String meetingCode) {
        this.name = name;
        this.meetingCode = meetingCode;
    }

    public Meeting toEntity() {
        return Meeting.builder()
                .name(name)
                .meetingCode(meetingCode)
                .build();
    }
}
