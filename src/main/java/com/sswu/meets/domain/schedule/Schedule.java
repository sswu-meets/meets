package com.sswu.meets.domain.schedule;

import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_no")
    private Meeting meeting;

    private String scheduleName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DateTuneState dateTuneState;   //일정 날짜&시간 조정 여부 'FIX' or 'DATES' or 'WEEKS'

    @Column
    private Boolean placeTuneState;   //일정 장소 조정 여부 true or false


    @Builder
    public Schedule(Meeting meeting, String scheduleName, DateTuneState dateTuneState, Boolean placeTuneState) {
        this.meeting = meeting;
        this.scheduleName = scheduleName;
        this.dateTuneState = dateTuneState;
        this.placeTuneState = placeTuneState;
    }

    public Schedule update(String scheduleName) {
        this.scheduleName = scheduleName;

        return this;
    }
}
