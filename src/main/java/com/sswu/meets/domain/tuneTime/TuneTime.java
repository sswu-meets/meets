package com.sswu.meets.domain.tuneTime;

import com.sswu.meets.domain.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TuneTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tuneTimeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_no")
    private Schedule schedule;

    private LocalDate tuneDate;

    private LocalTime tuneTime;

    private Long avPeopleNo;

    @Builder
    public TuneTime(Schedule schedule, LocalDate tuneDate, LocalTime tuneTime, Long avPeopleNo) {
        this.schedule = schedule;
        this.tuneDate = tuneDate;
        this.tuneTime = tuneTime;
        this.avPeopleNo = avPeopleNo;
    }

    public TuneTime updatePeopleNo(Long avPeopleNo) {
        this.avPeopleNo = avPeopleNo;

        return this;
    }
}
