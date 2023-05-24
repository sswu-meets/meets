package com.sswu.meets.dto.schedule.request;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.scheduleDateTune.ScheduleDateTune;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ScheduleDateTuneSaveRequestDto {
    private LocalDate tuneDate;

    public ScheduleDateTune toScheduleDateTune(Schedule schedule) {
        return ScheduleDateTune.builder()
                .schedule(schedule)
                .tuneDate(tuneDate)
                .build();
    }
}
