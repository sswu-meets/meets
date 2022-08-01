package com.sswu.meets.domain.tuneTime;

import com.sswu.meets.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TuneTimeRepository extends JpaRepository<TuneTime, Long> {
    TuneTime findByScheduleAndTuneDateAndTuneTime(Schedule schedule, LocalDate tuneDate, LocalTime tuneTime);
    TuneTime findBySchedule(Schedule schedule);
}
