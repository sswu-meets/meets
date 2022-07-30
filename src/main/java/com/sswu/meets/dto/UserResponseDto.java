package com.sswu.meets.dto;

import com.sswu.meets.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;

@Getter
//@NoArgsConstructor
public class UserResponseDto {
    @ApiModelProperty(example = "7")
    private Long user_no;

    @ApiModelProperty(example = "yj06283l4@gmail.com")
    private String email;

    @ApiModelProperty(example = "김유정")
    private String name;

    @ApiModelProperty(example = "https://lh3.googleusercontent.com/a-/AFdZucpR_TN-i3qs-t0dQk-LFdHqSIUq_Wvv10iwcAIKSw=s96-c")
    private String profile_url;

    public UserResponseDto(User user) {
        this.user_no = user.getUser_no();
        this.email = user.getEmail();
        this.name = user.getName();
        this.profile_url = user.getProfile_url();
    }
}
