package com.sswu.meets.dto;

import com.sswu.meets.domain.schedule.Schedule;
import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleResponseDto {
    private Long no;
    private Long meetingNo;
    private String scheduleName;
    private Boolean dateTuneState;
    private Boolean placeTuneState;
    private List<String> participants;

    public ScheduleResponseDto(Schedule entity){
        this.no = entity.getScheduleNo();
        this.meetingNo = entity.getMeeting() != null ? entity.getMeeting().getMeeting_no() : null;
        this.scheduleName = entity.getScheduleName();
        this.dateTuneState = entity.getDateTuneState();
        this.placeTuneState = entity.getPlaceTuneState();
    }

    public ScheduleResponseDto(Schedule entity, List<String> participants){
        this.no = entity.getScheduleNo();
        this.meetingNo = entity.getMeeting() != null ? entity.getMeeting().getMeeting_no() : null;
        this.scheduleName = entity.getScheduleName();
        this.dateTuneState = entity.getDateTuneState();
        this.placeTuneState = entity.getPlaceTuneState();
        this.participants = participants;
    }
}
