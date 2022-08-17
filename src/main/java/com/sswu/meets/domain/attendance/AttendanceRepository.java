package com.sswu.meets.domain.attendance;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAttendanceByUser (User user);

    List<Attendance> findAttendanceBySchedule (Schedule schedule);
}
