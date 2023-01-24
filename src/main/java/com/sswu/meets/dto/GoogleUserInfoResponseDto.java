package com.sswu.meets.dto;

import com.sswu.meets.domain.user.Role;
import com.sswu.meets.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GoogleUserInfoResponseDto {
    private String id;
    private String email;
    private Boolean verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String locale;

    public User toEntity() {
        return User
                .builder()
                .email(email)
                .name(name)
                .profileUrl(picture)
                .role(Role.USER)
                .build();
    }
}
