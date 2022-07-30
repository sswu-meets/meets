package com.sswu.meets.dto;

import com.sswu.meets.domain.scheduleDateTune.ScheduleDateTune;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ScheduleDateTuneResponseDto {
    private Long scheduleDateTuneNo;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public ScheduleDateTuneResponseDto(ScheduleDateTune entity) {
        this.scheduleDateTuneNo = entity.getScheduleDateTuneNo();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
    }
}
