package com.sswu.meets.dto;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.scheduleDateFix.ScheduleDateFix;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ScheduleDateFixSaveRequestDto {
    private LocalDate fixDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @Builder ScheduleDateFixSaveRequestDto(LocalDate fixDate, LocalTime startTime, LocalTime endTime) {
        this.fixDate = fixDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ScheduleDateFix toEntity(Schedule schedule) {
        return ScheduleDateFix.builder()
                .schedule(schedule)
                .fixDate(fixDate)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
