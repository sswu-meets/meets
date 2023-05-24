package com.sswu.meets.dto.schedule.request;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.scheduleDateFix.ScheduleDateFix;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ScheduleDateFixSaveRequestDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public ScheduleDateFix toScheduleDateFix(Schedule schedule) {
        return ScheduleDateFix.builder()
                .schedule(schedule)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
