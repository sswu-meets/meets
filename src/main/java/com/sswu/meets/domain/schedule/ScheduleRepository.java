package com.sswu.meets.domain.schedule;

import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByScheduleNoAndScheduleCode(Long scheduleNo, String scheduleCode);
    List<Schedule> findAll();
    List<Schedule> findByMeeting(Meeting meeting);
}