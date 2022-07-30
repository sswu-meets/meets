package com.sswu.meets.domain.todo;

import com.sswu.meets.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findBySchedule(Schedule schedule);
}
