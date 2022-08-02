package com.sswu.meets.domain.dateTune;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DateTuneRepository extends JpaRepository<DateTune, Long> {
    DateTune findByUserAndScheduleAndAvDate(User user, Schedule schedule, LocalDate avDate);
}
