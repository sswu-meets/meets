package com.sswu.meets.dto.schedule.request;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.scheduleDateTune.ScheduleDateTune;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TuneRangeScheduleSaveRequestDto extends ScheduleBaseRequestDto {

    @ApiModelProperty(example = "2023-05-10", dataType = "String")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @ApiModelProperty(example = "2023-05-15", dataType = "String")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public ScheduleDateTune toScheduleDateTune(Schedule schedule, LocalDate tuneDate) {
        return ScheduleDateTune.builder()
                .schedule(schedule)
                .tuneDate(tuneDate)
                .build();
    }

}
