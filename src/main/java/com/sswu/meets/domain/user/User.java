package com.sswu.meets.domain.user;

import com.sswu.meets.domain.attendance.Attendance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id // 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙
    private Long userNo;

    private String email;

    private String name;

    private String profileUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

//    @OneToMany(mappedBy = "user")
//    private List<Attendance> attendanceList = new ArrayList<>();

    @Builder
    public User(String email, String name, String profileUrl, Role role) {
        this.email = email;
        this.name = name;
        this.profileUrl = profileUrl;
        this.role = role;
    }

    public User update(String name, String profileUrl) {
        this.name = name;
        this.profileUrl = profileUrl;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
