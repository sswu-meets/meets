package com.sswu.meets.dto;

import com.sswu.meets.domain.schedule.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long schedule_no;
    private String schedule_name;    //일정 이름
    private Boolean date_tune;   //일정 날짜 조정 여부 Y or N
    private Boolean time_tune;   //일정 시간 조정 여부 Y or N
    private String schedule_memo;    //일정 메모

    public ScheduleResponseDto(Schedule entity){
        this.schedule_no = entity.getSchedule_no();
        this.schedule_name = entity.getSchedule_name();
        this.date_tune = entity.getDate_tune();
        this.time_tune = entity.getTime_tune();
        this.schedule_memo = entity.getSchedule_memo();
    }
}
