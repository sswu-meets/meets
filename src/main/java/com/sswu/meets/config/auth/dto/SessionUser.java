package com.sswu.meets.config.auth.dto;

import com.sswu.meets.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private Long userNo;
    private String email;
    private String name;
    private String profileUrl;

    public SessionUser(User user) {
        this.userNo = user.getUserNo();
        this.email = user.getEmail();
        this.name = user.getName();
        this.profileUrl = user.getProfileUrl();
    }
}
