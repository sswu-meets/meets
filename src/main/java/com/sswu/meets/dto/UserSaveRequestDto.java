package com.sswu.meets.dto;

import com.sswu.meets.domain.user.Role;
import com.sswu.meets.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

    @Email
    @ApiModelProperty(example = "test13@email.com")
    private String email;

    @ApiModelProperty(example = "test13")
    private String name;

    @ApiModelProperty(example = "https://lh3.googleusercontent.com/a/AItbvmkXhjMMGAvBGq0sYXoppX80njinx9QdfN7GW7hg=s96-c")
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
                .role(Role.USER)
                .build();
    }
}
