package com.sswu.meets.dto.schedule.request;

import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.schedule.DateTuneType;
import com.sswu.meets.domain.schedule.Schedule;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.OptionalLong;

@Getter
@NoArgsConstructor
public class ScheduleBaseRequestDto {
    String scheduleName;
    @ApiModelProperty(example = "4", dataType = "Long")
    OptionalLong meetingNo = OptionalLong.empty();
    Boolean placeTuneState;
    @ApiModelProperty(example = "09:00", dataType = "String")
    @DateTimeFormat(pattern = "kk:mm")
    LocalTime startTime;
    @ApiModelProperty(example = "21:00", dataType = "String")
    @DateTimeFormat(pattern = "kk:mm")
    LocalTime endTime;

    public Schedule toSchedule(Meeting meeting, DateTuneType dateTuneType) {
        return Schedule.builder()
                .meeting(meeting)
                .scheduleName(scheduleName)
                .dateTuneState(dateTuneType.isStatus())
                .startTime(startTime)
                .endTime(endTime)
                .placeTuneState(placeTuneState)
                .build();
    }
}
