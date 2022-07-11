package com.sswu.meets.config.auth.dto;

import com.sswu.meets.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String email;
    private String name;
    private String profile_url;

    public SessionUser(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.profile_url = user.getProfile_url();
    }
}
