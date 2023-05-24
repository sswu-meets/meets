package com.sswu.meets.domain.scheduleDateFix;

import com.sswu.meets.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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

    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public ScheduleDateFix(Schedule schedule, LocalDate startDate, LocalDate endDate) {
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
