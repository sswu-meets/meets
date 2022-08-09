package com.sswu.meets.dto;

import com.sswu.meets.domain.meeting.Meeting;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MeetingSaveRequestDto {
    @ApiModelProperty(example = "meets")
    private String name;

    @ApiModelProperty(example = "#ED7D31")
    private String meetingColor;

    @ApiModelProperty(example = "https://lh3.googleusercontent.com/a-/AFdZucpR_TN-i3qs-t0dQk-LFdHqSIUq_Wvv10iwcAIKSw=s96-c")
    private String meetingUrl;      // 일단 user profileUrl을 가져옴. 추후에 수정 예정

    @Builder
    public MeetingSaveRequestDto(String name, String meetingColor, String meetingUrl) {
        this.name = name;
        this.meetingColor = meetingColor;
        this.meetingUrl = meetingUrl;
    }

    public Meeting toEntity(String meetingCode) {
        return Meeting.builder()
                .name(name)
                .meetingCode(meetingCode)
                .meetingColor(meetingColor)
                .meetingUrl(meetingUrl)
                .build();
    }
}
