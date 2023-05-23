package com.sswu.meets.dto.schedule.response;

import com.sswu.meets.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScheduleResponseDto {
    private Long no;
    private Long meetingNo;
    private String scheduleName;
    private LocalDate fixStartDate; // 고정 일정인 경우 시작 날짜
    private LocalDate fixEndDate; // 고정 일정인 경우 종료 날짜
    private List<LocalDate> tuneDateList; // 종료 일정인 경우 날짜 리스트
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean dateTuneState;
    private Boolean placeTuneState;
    private List<String> participants;

    @Builder
    public ScheduleResponseDto(
            Schedule schedule,
            LocalDate fixStartDate,
            LocalDate fixEndDate,
            List<LocalDate> tuneDateList
    ) {
        this.no = schedule.getScheduleNo();
        this.meetingNo = schedule.getMeeting() != null ? schedule.getMeeting().getMeeting_no() : null;
        this.scheduleName = schedule.getScheduleName();
        this.fixStartDate = fixStartDate;
        this.fixEndDate = fixEndDate;
        this.tuneDateList = tuneDateList;
        this.startTime = schedule.getStartTime();
        this.endTime = schedule.getEndTime();
        this.dateTuneState = schedule.getDateTuneState();
        this.placeTuneState = schedule.getPlaceTuneState();
        this.participants = schedule.getAttendanceList().stream()
                .map(a -> a.getUser().getName())
                .collect(Collectors.toList());
    }

}
