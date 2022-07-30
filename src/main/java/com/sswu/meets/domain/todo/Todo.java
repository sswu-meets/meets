package com.sswu.meets.domain.todo;

import com.sswu.meets.domain.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no")
    private Schedule schedule;

    private String todoContent;     // 투두 내용

    private Boolean todoStatus;     // 실행 여부

    @Builder
    public Todo(Schedule schedule, String todoContent, Boolean todoStatus){
        this.schedule = schedule;
        this.todoContent = todoContent;
        this.todoStatus = todoStatus;
    }

}
