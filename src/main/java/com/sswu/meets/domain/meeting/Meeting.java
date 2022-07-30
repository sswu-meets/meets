package com.sswu.meets.domain.meeting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meeting_no;

    private String name;

    private String meetingCode;

    private String meetingColor;

    @Builder
    public Meeting(String name, String meetingCode, String meetingColor) {
        this.name = name;
        this.meetingCode = meetingCode;
        this.meetingColor = meetingColor;
    }

}
