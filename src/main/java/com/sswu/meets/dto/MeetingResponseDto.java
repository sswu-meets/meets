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

    @ApiModelProperty(example = "#ED7D31")
    private String meetingColor;

    @ApiModelProperty(example = "https://lh3.googleusercontent.com/a-/AFdZucpR_TN-i3qs-t0dQk-LFdHqSIUq_Wvv10iwcAIKSw=s96-c")
    private String meetingUrl;      // 일단 user profileUrl을 가져옴. 추후에 수정 예정

    public MeetingResponseDto(Meeting meetingEntity) {
        this.meeting_no = meetingEntity.getMeeting_no();
        this.name = meetingEntity.getName();
        this.meetingCode = meetingEntity.getMeetingCode();
        this.meetingColor = meetingEntity.getMeetingColor();
        this.meetingUrl = meetingEntity.getMeetingUrl();
    }
}
