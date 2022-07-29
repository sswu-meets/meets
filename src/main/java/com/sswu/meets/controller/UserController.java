package com.sswu.meets.controller;

import com.sswu.meets.config.auth.dto.SessionUser;
import com.sswu.meets.dto.UserResponseDto;
import com.sswu.meets.dto.UserSaveRequestDto;
import com.sswu.meets.dto.UserUpdateRequestDto;
import com.sswu.meets.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String hello() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if (sessionUser != null) {
            String userName = sessionUser.getName();
            return userName + "님 환영합니다.";
        } else {
            return "meets에 오신 걸 환영합니다:)";
        }
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

    // 마이페이지
    @GetMapping("/user/mypage")
    public Object getUserInfo() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if (sessionUser != null) {
            return sessionUser;
        } else {
            return "로그인을 먼저 해주세요.";
        }
    }

    // 유저 정보 수정
    @PutMapping("/user/{user_no}")
    public boolean update(@PathVariable Long user_no, @RequestBody UserUpdateRequestDto userSaveRequestDto){
        return userService.update(user_no, userSaveRequestDto);
    }

    // 유저 정보 삭제
    @DeleteMapping("/user/{user_no}")
    public boolean deleteUser(@PathVariable Long user_no) {
        return userService.deleteUser(user_no);
    }

    // 로그인
    @GetMapping("/user/login")
    public void login(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("/oauth2/authorization/google");
    }

    // 로그아웃
    @GetMapping("/user/logout")
    public void logout(HttpServletResponse httpServletResponse) throws IOException {
        httpSession.invalidate();
        httpServletResponse.sendRedirect("/");
    }

    // 로그인 유무
    @GetMapping("/user/status")
    public Boolean getUserStatus() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if (sessionUser != null) {
            return true;
        } else {
            return false;
        }
    }
}