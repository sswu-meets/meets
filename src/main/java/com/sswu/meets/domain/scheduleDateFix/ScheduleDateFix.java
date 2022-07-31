package com.sswu.meets.domain.scheduleDateFix;

import com.sswu.meets.domain.meeting.Meeting;
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
public class ScheduleDateFix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleDateFixNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_no")
    private Schedule schedule;

    private LocalDate fixDate;

    private LocalTime startTime;

    private LocalTime endTime;

    @Builder
    public ScheduleDateFix(Schedule schedule, LocalDate fixDate, LocalTime startTime, LocalTime endTime) {
        this.schedule = schedule;
        this.fixDate = fixDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
