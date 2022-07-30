package com.sswu.meets.dto;

import com.sswu.meets.domain.todo.Todo;
import lombok.Getter;

@Getter
public class TodoResponseDto {
    private Long todoNo;
    private String todoContent;
    private Boolean todoStatus;

    public TodoResponseDto(Todo entity) {
        this.todoNo = entity.getTodoNo();
        this.todoContent = entity.getTodoContent();
        this.todoStatus = entity.getTodoStatus();
    }
}
