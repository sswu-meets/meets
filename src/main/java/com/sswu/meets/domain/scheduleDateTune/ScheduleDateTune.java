package com.sswu.meets.domain.scheduleDateTune;

import com.sswu.meets.domain.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.util.Date;
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

    private LocalDate startDate;        // java.time 클래스의 LocalDate, LocalTime
                                        // LocalDateTime으로 동시에 받아도 좋을 듯 함 (논의)
    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

    @Builder
    public ScheduleDateTune(Schedule schedule, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
