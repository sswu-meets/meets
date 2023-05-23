package com.sswu.meets.domain.scheduleDateTune;

import com.sswu.meets.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleDateTuneRepository extends JpaRepository<ScheduleDateTune, Long> {
    @Query("select s "
            + "from ScheduleDateTune s "
            + "join fetch s.schedule "
            + "where s.schedule = :schedule ")
    List<ScheduleDateTune> findBySchedule(@Param("schedule") Schedule schedule);
}
