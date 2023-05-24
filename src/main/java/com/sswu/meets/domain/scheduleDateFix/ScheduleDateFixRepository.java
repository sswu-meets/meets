package com.sswu.meets.domain.scheduleDateFix;

import com.sswu.meets.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleDateFixRepository extends JpaRepository<ScheduleDateFix, Long> {
    public ScheduleDateFix findBySchedule(Schedule schedule);
}
