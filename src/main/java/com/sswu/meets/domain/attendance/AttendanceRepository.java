package com.sswu.meets.domain.attendance;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query("select a "
            + "from Attendance a "
            + "join fetch a.schedule "
            + "where a.user = :user ")
    List<Attendance> findAttendanceByUser (@Param("user") User user);

    List<Attendance> findAttendanceBySchedule (Schedule schedule);
}
