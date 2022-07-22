package com.sswu.meets.dto;

import com.sswu.meets.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleUpdateRequestDto {
    private String scheduleName;    //일정 이름

    @Builder
    public ScheduleUpdateRequestDto(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public Schedule toEntity() {
        return Schedule.builder()
                .scheduleName(scheduleName)
                .build();
    }
}
