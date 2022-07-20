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

    @Builder
    public User(String email, String name, String profile_url) {
        this.email = email;
        this.name = name;
        this.profile_url = profile_url;
    }
    
    public void update(String email, String name, String profile_url) {
        this.email = email;
        this.name = name;
        this.profile_url = profile_url;
    }
}
