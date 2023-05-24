package com.sswu.meets.domain.scheduleDateTune;

import com.sswu.meets.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity
public class ScheduleDateTune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleDateTuneNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_no")
    private Schedule schedule;
    private LocalDate tuneDate;

    @Builder
    public ScheduleDateTune(Schedule schedule, LocalDate tuneDate) {
        this.schedule = schedule;
        this.tuneDate = tuneDate;
    }
}
