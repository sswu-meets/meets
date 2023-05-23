package com.sswu.meets.domain.scheduleDateFix;

import com.sswu.meets.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleDateFixRepository extends JpaRepository<ScheduleDateFix, Long> {
    @Query("select s "
            + "from ScheduleDateFix s "
            + "join fetch s.schedule "
            + "where s.schedule = :schedule ")
    public ScheduleDateFix findBySchedule(@Param("schedule") Schedule schedule);
}
