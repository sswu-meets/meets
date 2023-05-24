package com.sswu.meets.domain.schedule;

import com.sswu.meets.domain.attendance.Attendance;
import com.sswu.meets.domain.meeting.Meeting;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_no")
    private Meeting meeting;

    private String scheduleCode = UUID.randomUUID().toString().substring(0, 8);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule")
    private List<Attendance> attendanceList = new ArrayList<>();

    private String scheduleName;
    private LocalTime startTime;
    private LocalTime endTime;

    private Boolean dateTuneState;   // 조율 -> true, 고정 -> false

    private Boolean placeTuneState;   // 일정 장소 조정 여부 true or false


    @Builder
    public Schedule(
            Meeting meeting,
            String scheduleName,
            LocalTime startTime,
            LocalTime endTime,
            Boolean dateTuneState,
            Boolean placeTuneState
    ) {
        this.meeting = meeting;
        this.scheduleName = scheduleName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dateTuneState = dateTuneState;
        this.placeTuneState = placeTuneState;
    }

    public Schedule update(String scheduleName) {
        this.scheduleName = scheduleName;

        return this;
    }

}
