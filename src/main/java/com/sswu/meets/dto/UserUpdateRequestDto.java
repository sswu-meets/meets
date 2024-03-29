package com.sswu.meets.dto;

import com.sswu.meets.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    @ApiModelProperty(example = "yj06283l4@gmail.com")
    private String email;

    @ApiModelProperty(example = "김유정")
    private String name;

    @ApiModelProperty(example = "https://lh3.googleusercontent.com/a-/AFdZucpR_TN-i3qs-t0dQk-LFdHqSIUq_Wvv10iwcAIKSw=s96-c")
    private String profileUrl;

    @Builder
    public UserUpdateRequestDto(String email, String name, String profileUrl){
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
