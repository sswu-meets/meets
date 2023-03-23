package com.sswu.meets.dto;

import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.OptionalLong;

@Getter
@NoArgsConstructor
public class FixScheduleSaveRequestDto {
    private String scheduleName;
    private OptionalLong meetingNo = OptionalLong.empty();
    private Boolean placeTuneState;
    private String fixDate;
    private String startTime;
    private String endTime;

    @Builder
    public FixScheduleSaveRequestDto(String scheduleName, Long meetingNo, Boolean placeTuneState, String fixDate, String startTime, String endTime) {
        this.scheduleName = scheduleName;
        this.meetingNo = OptionalLong.of(meetingNo);
        this.placeTuneState = placeTuneState;
        this.fixDate = fixDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Schedule toEntity(Meeting meeting) {
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

        return ScheduleDateFixSaveRequestDto.builder()
                .fixDate(fixDate)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }

}