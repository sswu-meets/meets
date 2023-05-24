package com.sswu.meets.dto.schedule.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TuneScheduleSaveRequestDto extends ScheduleBaseRequestDto {
    private List<String> tuneDateList;

}