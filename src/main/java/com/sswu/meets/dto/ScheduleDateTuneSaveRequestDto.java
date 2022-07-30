package com.sswu.meets.dto;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.scheduleDateTune.ScheduleDateTune;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ScheduleDateTuneSaveRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @Builder
    public ScheduleDateTuneSaveRequestDto(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime){
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ScheduleDateTune toEntity(Schedule schedule) {
        return ScheduleDateTune.builder()
                .schedule(schedule)
                .startDate(startDate)
                .endDate(endDate)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
