package com.sswu.meets.controller;

import com.sswu.meets.dto.UserResponseDto;
import com.sswu.meets.dto.UserSaveRequestDto;
import com.sswu.meets.dto.UserUpdateRequestDto;
import com.sswu.meets.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

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
    @PutMapping("/user")
    public Boolean update(@RequestBody UserUpdateRequestDto userSaveRequestDto){
        return userService.update(userSaveRequestDto);
    }

    // 유저 정보 삭제
//    @Bean
//    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
//        return new HiddenHttpMethodFilter();
//    }

    @DeleteMapping("/delete/{user_id}")
    public void delete(@PathVariable String user_id, @RequestParam String email, String name, String profile_url) {
        System.out.println(user_id);
        System.out.println(email);
        System.out.println(name);
        System.out.println(profile_url);
    }
}