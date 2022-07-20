package com.sswu.meets.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id // 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙
    private Long user_no;

    private String email;

    private String name;

    private String profile_url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String email, String name, String profile_url, Role role) {
        this.email = email;
        this.name = name;
        this.profile_url = profile_url;
        this.role = role;
    }

    public User update(String name, String profile_url) {
        this.name = name;
        this.profile_url = profile_url;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
