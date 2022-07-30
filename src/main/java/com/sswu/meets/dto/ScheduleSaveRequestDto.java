package com.sswu.meets.dto;

import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleSaveRequestDto {
    private String scheduleName;
    private Boolean dateTuneState;
    private Boolean placeTuneState;

    @Builder
    public ScheduleSaveRequestDto(String scheduleName, Boolean dateTuneState, Boolean placeTuneState) {
        this.scheduleName = scheduleName;
        this.dateTuneState = dateTuneState;
        this.placeTuneState = placeTuneState;
    }

    public Schedule toEntity(Meeting meeting) {
        System.out.println("ScheduleSaveRequestDto.toEntity()");
        return Schedule.builder()
                .meeting(meeting)
                .scheduleName(scheduleName)
                .dateTuneState(dateTuneState)
                .placeTuneState(placeTuneState)
                .build();
    }

}