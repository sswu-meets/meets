package com.sswu.meets.domain.schedule;

import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAll();
    List<Schedule> findByMeeting(Meeting meeting);
}