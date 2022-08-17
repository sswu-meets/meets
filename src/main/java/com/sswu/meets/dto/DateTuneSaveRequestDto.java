package com.sswu.meets.dto;

import com.sswu.meets.domain.avTime.AvTime;
import com.sswu.meets.domain.dateTune.DateTune;
import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.tuneTime.TuneTime;
import com.sswu.meets.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class DateTuneSaveRequestDto {
    @ApiModelProperty(example = "2022-08-17")
    private String avDate;

    @ApiModelProperty(example = "09:00")
    private String[] avTime;

    @Builder
    public DateTuneSaveRequestDto(String avDate, String[] avTime) {
        this.avDate = avDate;
        this.avTime = avTime;
    }

    public DateTune toEntity(User user, Schedule schedule) {
        return DateTune.builder()
                .user(user)
                .schedule(schedule)
                .avDate(changeDateFormat(avDate))
                .build();
    }

    public AvTime toAvTime(DateTune dateTune, String avTime) {
        return AvTime.builder()
                .dateTune(dateTune)
                .avTime(changeTimeFormat(avTime))
                .build();
    }

    public TuneTime toTuneTime(Schedule schedule, String avTime, Long avPeopleNo) {
        return TuneTime.builder()
                .schedule(schedule)
                .tuneDate(changeDateFormat(avDate))
                .tuneTime(changeTimeFormat(avTime))
                .avPeopleNo(avPeopleNo)
                .build();
    }

    public LocalDate changeDateFormat(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate resultDate = LocalDate.parse(date, dateFormatter);

        return resultDate;
    }

    public LocalTime changeTimeFormat(String time) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("kk:mm");

        LocalTime resultTime = LocalTime.parse(time, timeFormat);

        return resultTime;
    }
}
