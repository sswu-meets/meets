package com.sswu.meets.domain.todo;

import com.mysql.cj.protocol.ColumnDefinition;
import com.sswu.meets.domain.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_no")
    private Schedule schedule;

    private String todoContent;     // 투두 내용

    @Column(columnDefinition = "Boolean default false")
    private Boolean todoStatus;     // 실행 여부

    @Builder
    public Todo(Schedule schedule, String todoContent, Boolean todoStatus){
        this.schedule = schedule;
        this.todoContent = todoContent;
        this.todoStatus = todoStatus;
    }

}
