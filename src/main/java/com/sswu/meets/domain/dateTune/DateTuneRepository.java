package com.sswu.meets.domain.dateTune;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateTuneRepository extends JpaRepository<DateTune, Long> {
    DateTune findByUserAndSchedule(User user, Schedule schedule);
}
