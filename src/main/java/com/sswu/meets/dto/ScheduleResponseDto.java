package com.sswu.meets.dto;

import com.sswu.meets.domain.schedule.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long no;
    private Long meetingNo;
    private String scheduleName;
    private Boolean dateTuneState;
    private Boolean placeTuneState;

    public ScheduleResponseDto(Schedule entity){
        this.no = entity.getNo();
        this.meetingNo = entity.getMeeting().getMeeting_no();
        this.scheduleName = entity.getScheduleName();
        this.dateTuneState = entity.getDateTuneState();
        this.placeTuneState = entity.getPlaceTuneState();
    }
}
