package com.sswu.meets.controller;

import com.sswu.meets.domain.tuneTime.TuneTimeRepository;
import com.sswu.meets.dto.Best3ResponseDto;
import com.sswu.meets.service.TuneTimeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiIgnore
@RequiredArgsConstructor
@RestController
public class TuneTimeController {

    private final TuneTimeService tuneTimeService;

    @ApiOperation(value = "가능한 시간 BEST3")
    @GetMapping("/tune/best/{scheduleNo}")
    public List<Best3ResponseDto> calculateBestTime(@PathVariable Long scheduleNo) {
        return tuneTimeService.calculateBestTime(scheduleNo);
    }
}
