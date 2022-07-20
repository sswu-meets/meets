package com.sswu.meets.dto;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleSaveRequestDto {
    public Object getTitle;
    private String schedule_name;
    private Boolean date_tune;
    private Boolean time_tune;
    private String schedule_memo;

    @Builder
    public ScheduleSaveRequestDto(String schedule_name, Boolean date_tune, Boolean time_tune, String schedule_memo) {
        this.schedule_name = schedule_name;
        this.date_tune = date_tune;
        this.time_tune = time_tune;
        this.schedule_memo = schedule_memo;
    }

    public Schedule toEntity(){
        return Schedule.builder()
                .schedule_name(schedule_name)
                .date_tune(date_tune)
                .time_tune(time_tune)
                .schedule_memo(schedule_memo)
                .build();
    }

}