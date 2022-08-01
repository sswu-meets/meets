package com.sswu.meets.domain.scheduleDateTune;

import com.sswu.meets.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleDateTuneRepository extends JpaRepository<ScheduleDateTune,Long> {
    List<ScheduleDateTune> findBySchedule(Schedule schedule);
}
