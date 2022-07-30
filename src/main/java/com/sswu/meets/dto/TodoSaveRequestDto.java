package com.sswu.meets.dto;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.todo.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoSaveRequestDto {
    private String todoContent;
    private Boolean todoStatus;

    @Builder
    public TodoSaveRequestDto(String todoContent, Boolean todoStatus){
        this.todoContent = todoContent;
        this.todoStatus = todoStatus;
    }

    public Todo toEntity(Schedule schedule) {
        System.out.println(("TodoSaveRequestDto.toEntity()"));
        Todo build = Todo.builder()
                .schedule(schedule)
                .todoContent(todoContent)
                .todoStatus(todoStatus)
                .build();
        return build;
    }
}
