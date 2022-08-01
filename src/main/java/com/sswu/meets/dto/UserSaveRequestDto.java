package com.sswu.meets.dto;

import com.sswu.meets.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    public Object getTitle;
    private String email;
    private String name;
    private String profileUrl;

    @Builder
    public UserSaveRequestDto(String email, String name, String profileUrl) {
        this.email = email;
        this.name = name;
        this.profileUrl = profileUrl;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .profileUrl(profileUrl)
                .build();
    }
}
