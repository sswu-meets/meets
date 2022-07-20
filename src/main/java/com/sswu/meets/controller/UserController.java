package com.sswu.meets.controller;

import com.sswu.meets.domain.user.User;
import com.sswu.meets.dto.ScheduleUpdateRequestDto;
import com.sswu.meets.dto.UserResponseDto;
import com.sswu.meets.dto.UserSaveRequestDto;
import com.sswu.meets.dto.UserUpdateRequestDto;
import com.sswu.meets.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String hello() {
        return "meets에 오신 걸 환영합니다:)";
    }

    // 유저 등록
    @PostMapping("/user")
    public Long save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return userService.save(userSaveRequestDto);
    }

    // 모든 유저 정보 조회
    @GetMapping("/user")
    public List<UserResponseDto> getUserList() {
        return userService.getUserList();
    }

    // 유저 정보 수정
    @PutMapping("/user/{user_no}")
    public Boolean update(@PathVariable Long user_no, @RequestBody UserUpdateRequestDto userSaveRequestDto){
        return userService.update(user_no, userSaveRequestDto);
    }

    // 유저 정보 삭제
    @DeleteMapping("/user/{user_no}")
    public boolean deleteUser(@PathVariable Long user_no) {
        return userService.deleteUser(user_no);
    }
}