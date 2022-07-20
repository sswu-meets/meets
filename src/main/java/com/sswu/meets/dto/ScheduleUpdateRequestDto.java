package com.sswu.meets.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleUpdateRequestDto {
    private String schedule_name;    //일정 이름
    private Boolean date_tune;   //일정 날짜 조정 여부 Y or N
    private Boolean time_tune;   //일정 시간 조정 여부 Y or N
    private String schedule_memo;    //일정 메모

    @Builder
    public ScheduleUpdateRequestDto(String schedule_name, Boolean date_tune, Boolean time_tune, String schedule_memo) {
        this.schedule_name = schedule_name;
        this.date_tune = date_tune;
        this.time_tune = time_tune;
        this.schedule_memo = schedule_memo;
    }
}
