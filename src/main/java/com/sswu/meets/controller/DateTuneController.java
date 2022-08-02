package com.sswu.meets.controller;

import com.sswu.meets.config.auth.dto.SessionUser;
import com.sswu.meets.dto.DateTuneSaveRequestDto;
import com.sswu.meets.service.DateTuneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags = "조율")
@RequiredArgsConstructor
@RestController
public class DateTuneController {
    private final DateTuneService dateTuneService;
    private final HttpSession httpSession;

    @ApiOperation(value = "조율 데이터 저장")
    @PostMapping("/tune/{scheduleNo}")
    public Boolean saveAvDateAndTime(@PathVariable Long scheduleNo, @RequestBody List<DateTuneSaveRequestDto> requestDtoList) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        return dateTuneService.saveAvDateAndTime(sessionUser.getUserNo(), scheduleNo, requestDtoList);
    }
}
