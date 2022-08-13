package com.sswu.meets.domain.attendance;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attendance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_no")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @Builder
    public Attendance(Schedule schedule, User user) {
        this.schedule = schedule;
        this.user = user;
    }
}
