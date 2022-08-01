package com.sswu.meets.dto;

import com.sswu.meets.domain.todo.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoUpdateRequestDto {
    private String todoContent;
    private Boolean todoStatus;

    @Builder
    public  TodoUpdateRequestDto(String todoContent, Boolean todoStatus) {
        this.todoContent = todoContent;
        this.todoStatus = todoStatus;
    }

}
