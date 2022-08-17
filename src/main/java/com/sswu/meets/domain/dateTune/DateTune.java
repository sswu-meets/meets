package com.sswu.meets.domain.dateTune;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DateTune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dateTuneNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_no")
    private Schedule schedule;

    private LocalDate avDate;

    @Builder
    public DateTune(User user, Schedule schedule, LocalDate avDate) {
        this.user = user;
        this.schedule = schedule;
        this.avDate = avDate;
    }

}
