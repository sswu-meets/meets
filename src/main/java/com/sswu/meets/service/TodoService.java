package com.sswu.meets.service;

import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.schedule.ScheduleRepository;
import com.sswu.meets.domain.todo.Todo;
import com.sswu.meets.domain.todo.TodoRepository;
import com.sswu.meets.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long saveTodo(Long scheduleNo, TodoSaveRequestDto requestDto) {
        Schedule schedule = scheduleRepository.getById(scheduleNo);
        return todoRepository.save(requestDto.toEntity(schedule)).getTodoNo();
    }

    @Transactional
    public List<TodoResponseDto> getTodoList(Long scheduleNo) {
        Schedule schedule = scheduleRepository.getById(scheduleNo);
        return todoRepository.findBySchedule(schedule).stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public Boolean deleteTodo(Long todoNo) {
        Todo todo = todoRepository.getById(todoNo);
        try {
            todoRepository.delete(todo);
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e);
            return false;
        }
    }

//    public Boolean updateTodo(Long todoNo, TodoUpdateRequestDto requestDto) {
//        Todo todo = todoRepository.getById(todoNo);
//        try {
//            todo.update(requestDto.getTodoContent(), requestDto.getTodoStatus());
//            return true;
//        } catch (Exception e) {
//            System.out.println("error: " + e);
//            return false;
//        }
//    }

    @Transactional
    public Boolean updateTodo(Long todoNo, TodoUpdateRequestDto requestDto) {
        Todo todo = todoRepository.findById(todoNo)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 투두가 없습니다. todoNo=" + todoNo));
        todo.update(requestDto.getTodoContent(), requestDto.getTodoStatus());

        return true;
    }
}
