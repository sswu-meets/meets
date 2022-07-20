package com.sswu.meets.dto;

import com.sswu.meets.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@NoArgsConstructor
public class UserResponseDto {
    private Long user_no;
    private String email;
    private String name;
    private String profile_url;

    public UserResponseDto(User user) {
        this.user_no = user.getUser_no();
        this.email = user.getEmail();
        this.name = user.getName();
        this.profile_url = user.getProfile_url();
    }
}
