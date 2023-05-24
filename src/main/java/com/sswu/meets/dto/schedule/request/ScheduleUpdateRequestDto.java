package com.sswu.meets.dto.schedule.request;

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

    public Schedule toSchedule() {
        return Schedule.builder()
                .scheduleName(scheduleName)
                .build();
    }
}
