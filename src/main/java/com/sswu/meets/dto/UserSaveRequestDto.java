package com.sswu.meets.dto;

import com.sswu.meets.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String email;
    private String name;
    private String profile_url;

    @Builder
    public UserSaveRequestDto(String email, String name, String profile_url) {
        this.email = email;
        this.name = name;
        this.profile_url = profile_url;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .profile_url(profile_url)
                .build();
    }
}
