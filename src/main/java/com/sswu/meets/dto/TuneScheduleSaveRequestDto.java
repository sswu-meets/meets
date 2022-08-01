package com.sswu.meets.dto;

import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class TuneScheduleSaveRequestDto {
    private String scheduleName;
    private Boolean placeTuneState;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;

    @Builder
    public TuneScheduleSaveRequestDto(String scheduleName,Boolean placeTuneState, String startDate, String endDate, String startTime, String endTime) {
        this.scheduleName = scheduleName;
        this.placeTuneState = placeTuneState;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Schedule toEntity(Meeting meeting) {
        System.out.println("ScheduleSaveRequestDto.toEntity()");
        return Schedule.builder()
                .meeting(meeting)
                .scheduleName(scheduleName)
                .dateTuneState(true)
                .placeTuneState(placeTuneState)
                .build();
    }

    public ScheduleDateTuneSaveRequestDto changeFormat() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("kk:mm");

        LocalDate startDate = LocalDate.parse(this.startDate, dateFormatter);
        LocalDate endDate = LocalDate.parse(this.endDate, dateFormatter);
        LocalTime startTime = LocalTime.parse(this.startTime, timeFormat);
        LocalTime endTime = LocalTime.parse(this.endTime, timeFormat);
        System.out.println("시작일: " + startDate);
        System.out.println("종료일: " + endDate);

        return ScheduleDateTuneSaveRequestDto.builder()
                .startDate(startDate)
                .endDate(endDate)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }

}