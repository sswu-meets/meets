package com.sswu.meets.controller;

import com.sswu.meets.dto.TodoSaveRequestDto;
import com.sswu.meets.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "투두")
@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoService todoService;

    @ApiOperation(value = "투두 리스트 등록", notes = "투두 고유 식별 번호 반환")
    @ApiImplicitParam(name = "scheduleNo", value = "일정 고유 식별 번호")
    @PostMapping("/todo/{scheduleNo}")
    public Long save(@PathVariable Long scheduleNo, @RequestBody TodoSaveRequestDto requestDto) {
        return todoService.save(scheduleNo,requestDto);
    }
}
