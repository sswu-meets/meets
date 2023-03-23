package com.sswu.meets.service;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.schedule.ScheduleRepository;
import com.sswu.meets.domain.todo.Todo;
import com.sswu.meets.domain.todo.TodoRepository;
import com.sswu.meets.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final ScheduleRepository scheduleRepository;

    // 투두 리스트 등록
    @Transactional
    public Long saveTodo(Long scheduleNo, TodoSaveRequestDto requestDto) {
        Schedule schedule = scheduleRepository.getById(scheduleNo);
        return todoRepository.save(requestDto.toEntity(schedule)).getTodoNo();
    }

    // 투두 리스트 조회
    @Transactional
    public List<TodoResponseDto> getTodoList(Long scheduleNo) {
        Schedule schedule = scheduleRepository.getById(scheduleNo);
        return todoRepository.findBySchedule(schedule).stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());

    }

    // 투두 리스트 삭제
    @Transactional
    public Boolean deleteTodo(Long todoNo) {
        Todo todo = todoRepository.getById(todoNo);
        try {
            todoRepository.delete(todo);
            return true;
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            return false;
        }
    }

    // 투두 리스트 부분 수정
    @Transactional
    public Boolean updateTodo(Long todoNo, TodoUpdateRequestDto requestDto) {
        Todo todo = todoRepository.getById(todoNo);
        if(requestDto.getTodoContent() != null) {
            todo.updateContent(requestDto.getTodoContent());
        }

        if (requestDto.getTodoStatus() != null) {
            todo.updateStatus(requestDto.getTodoStatus());
        }

        return true;
    }
}
