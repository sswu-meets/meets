package com.sswu.meets.dto;

import com.sswu.meets.domain.schedule.DateTuneState;
import com.sswu.meets.domain.schedule.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long no;
    private String scheduleName;
    private DateTuneState dateTuneState;
    private Boolean placeTuneState;

    public ScheduleResponseDto(Schedule entity){
        this.no = entity.getNo();
        this.scheduleName = entity.getScheduleName();
        this.dateTuneState = entity.getDateTuneState();
        this.placeTuneState = entity.getPlaceTuneState();
    }
}
