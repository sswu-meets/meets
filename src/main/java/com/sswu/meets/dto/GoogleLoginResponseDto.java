package com.sswu.meets.dto;

import com.sswu.meets.domain.user.User;
import lombok.Getter;

@Getter
public class GoogleLoginResponseDto {
    private String email;
    private String name;
    private String profileUrl;

    public GoogleLoginResponseDto(User userEntity) {
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        this.profileUrl = userEntity.getProfileUrl();
    }
}
