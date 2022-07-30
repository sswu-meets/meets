package com.sswu.meets.domain.participation;

import com.sswu.meets.domain.meeting.Meeting;
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
public class Participation implements Serializable {

    @Id // 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙
<<<<<<< HEAD
    private Long no;
=======
    private Long participation_no;
>>>>>>> main

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_no")
    private Meeting meeting;

    @Builder
    public Participation(User user, Meeting meeting) {
        this.user = user;
        this.meeting = meeting;
    }

}