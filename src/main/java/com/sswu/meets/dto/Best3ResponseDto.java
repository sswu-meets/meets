package com.sswu.meets.dto;

import com.sswu.meets.domain.tuneTime.TuneTime;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class Best3ResponseDto {

    @ApiModelProperty(example = "2022-08-17")
    private LocalDate bestDate;

    @ApiModelProperty(example = "11:00")
    private LocalTime startTime;

    @ApiModelProperty(example = "13:00")
    private LocalTime endTime;

    @ApiModelProperty(example = "3")
    private Long avPeopleNo;

    @Builder
    public Best3ResponseDto(LocalDate bestDate, LocalTime startTime, LocalTime endTime, Long avPeopleNo) {
        this.bestDate = bestDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.avPeopleNo = avPeopleNo;
    }

    public Best3ResponseDto updateEndTime(LocalTime endTime) {
        this.endTime = endTime;

        return this;
    }

    public Best3ResponseDto toBest3Item(TuneTime tuneTime) {
        return Best3ResponseDto.builder()
                .bestDate(tuneTime.getTuneDate())
                .startTime(tuneTime.getTuneTime())
                .endTime(tuneTime.getTuneTime().plusMinutes(30))
                .avPeopleNo(tuneTime.getAvPeopleNo())
                .build();
    }
}
