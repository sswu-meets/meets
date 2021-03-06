package com.sswu.meets.domain.schedule;

import com.sswu.meets.domain.meeting.Meeting;
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

    private Boolean dateTuneState;   // 조율 -> true, 고정 -> false

    private Boolean placeTuneState;   // 일정 장소 조정 여부 true or false


    @Builder
    public Schedule(Meeting meeting, String scheduleName, Boolean dateTuneState, Boolean placeTuneState) {
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
