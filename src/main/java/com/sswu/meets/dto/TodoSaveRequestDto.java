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

    @Builder
    public TodoSaveRequestDto(String todoContent){
        this.todoContent = todoContent;
    }

    public Todo toEntity(Schedule schedule) {
        System.out.println(("TodoSaveRequestDto.toEntity()"));
        Todo build = Todo.builder()
                .schedule(schedule)
                .todoContent(todoContent)
                .build();
        return build;
    }
}
