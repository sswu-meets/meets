package com.sswu.meets.controller;

import com.sswu.meets.dto.TodoSaveRequestDto;
import com.sswu.meets.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoService todoService;

    // 투두 리스트 등록
    @PostMapping("/todo/{scheduleNo}")
    public Long save(@PathVariable Long scheduleNo, @RequestBody TodoSaveRequestDto requestDto) {
        return todoService.save(scheduleNo,requestDto);
    }
}
