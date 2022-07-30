package com.sswu.meets.dto;

import com.sswu.meets.domain.meeting.Meeting;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MeetingResponseDto {
    @ApiModelProperty(example = "8")
    private Long meeting_no;

    @ApiModelProperty(example = "소웨경")
    private String name;

    @ApiModelProperty(example = "3LB58f7MkS")
    private String meetingCode;

    public MeetingResponseDto(Meeting meetingEntity) {
        this.meeting_no = meetingEntity.getMeeting_no();
        this.name = meetingEntity.getName();
        this.meetingCode = meetingEntity.getMeetingCode();
    }
}
