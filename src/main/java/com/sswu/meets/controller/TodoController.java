package com.sswu.meets.controller;

import com.sswu.meets.dto.TodoResponseDto;
import com.sswu.meets.dto.TodoSaveRequestDto;
import com.sswu.meets.dto.TodoUpdateRequestDto;
import com.sswu.meets.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoService todoService;

    // 투두 리스트 등록
    @PostMapping("/todo/{scheduleNo}")
    public Long save(@PathVariable Long scheduleNo, @RequestBody TodoSaveRequestDto requestDto) {
        return todoService.saveTodo(scheduleNo,requestDto);
    }

    // 특정 일정의 투투 리스트 조회
    @GetMapping("/todo/{scheduleNo}")
    public List<TodoResponseDto> getTodoList(@PathVariable Long scheduleNo) {
        return todoService.getTodoList(scheduleNo);
    }

    // 투두 리스트 삭제
    @DeleteMapping("/todo/{todoNo}")
    public Boolean deleteTodo(@PathVariable Long todoNo) {
        return todoService.deleteTodo(todoNo);
    }

    // 투두 리스트 수정
    @PatchMapping("/todo/{todoNo}")     // 내용과 실행 여부를 각각 수정 가능
    public Boolean updateTodo(@PathVariable Long todoNo, @RequestBody TodoUpdateRequestDto requestDto) {
        return todoService.updateTodo(todoNo, requestDto);
    }

}
