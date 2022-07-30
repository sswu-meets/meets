package com.sswu.meets.service;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.schedule.ScheduleRepository;
import com.sswu.meets.domain.todo.TodoRepository;
import com.sswu.meets.dto.TodoSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long save(Long scheduleNo, TodoSaveRequestDto requestDto) {
        Schedule schedule = scheduleRepository.getById(scheduleNo);
        return todoRepository.save(requestDto.toEntity(schedule)).getTodoNo();
    }
}
