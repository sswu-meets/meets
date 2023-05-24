package com.sswu.meets.dto.schedule.response;

import com.sswu.meets.domain.scheduleDateTune.ScheduleDateTune;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleDateTuneResponseDto {
    private Long scheduleDateTuneNo;
    private Long scheduleNo;
    private LocalDate tuneDate;

    public ScheduleDateTuneResponseDto(ScheduleDateTune entity) {
        this.scheduleDateTuneNo = entity.getScheduleDateTuneNo();
        this.scheduleNo = entity.getSchedule().getScheduleNo();
        this.tuneDate = entity.getTuneDate();
    }
}
