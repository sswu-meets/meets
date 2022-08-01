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
public class FixScheduleSaveRequestDto {
    private String scheduleName;
    private Boolean placeTuneState;
    private String fixDate;
    private String startTime;
    private String endTime;

    @Builder
    public FixScheduleSaveRequestDto(String scheduleName, Boolean placeTuneState, String fixDate, String startTime, String endTime) {
        this.scheduleName = scheduleName;
        this.placeTuneState = placeTuneState;
        this.fixDate = fixDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Schedule toEntity(Meeting meeting) {
        System.out.println("ScheduleSaveRequestDto.toEntity()");
        return Schedule.builder()
                .meeting(meeting)
                .scheduleName(scheduleName)
                .dateTuneState(false)
                .placeTuneState(placeTuneState)
                .build();
    }

    public ScheduleDateFixSaveRequestDto changeFormat() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("kk:mm");

        LocalDate fixDate = LocalDate.parse(this.fixDate, dateFormatter);
        LocalTime startTime = LocalTime.parse(this.startTime, timeFormat);
        LocalTime endTime = LocalTime.parse(this.endTime, timeFormat);
        System.out.println("일정 날짜: " + fixDate);

        return ScheduleDateFixSaveRequestDto.builder()
                .fixDate(fixDate)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }

}