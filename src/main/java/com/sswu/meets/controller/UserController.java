package com.sswu.meets.controller;

import com.sswu.meets.config.auth.dto.SessionUser;
import com.sswu.meets.dto.UserResponseDto;
import com.sswu.meets.dto.UserSaveRequestDto;
import com.sswu.meets.dto.UserUpdateRequestDto;
import com.sswu.meets.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Api(tags = "유저")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;

    @ApiOperation(value = "홈 api", notes = "로그인 한 유저의 경우, OO님 환영합니다. | 로그인 하지 않은 유저의 경우, \"meets에 오신 걸 환영합니다:)\"")
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

    @ApiOperation(value = "유저 등록", notes = "유저 고유 식별 번호 반환")
    @PostMapping("/user")
    public Long save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return userService.save(userSaveRequestDto);
    }

    @ApiOperation(value = "모든 유저 정보 조회")
    @GetMapping("/user")
    public List<UserResponseDto> getUserList() {
        return userService.getUserList();
    }

    @ApiOperation(value = "마이페이지", notes = "\"로그인 한 유저의 경우, 유저 정보 반환 | 로그인 하지 않은 유저의 경우, \"로그인을 먼저 해주세요.\"")
    @GetMapping("/user/mypage")
    public Object getUserInfo() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if (sessionUser != null) {
            return sessionUser;
        } else {
            return "로그인을 먼저 해주세요.";
        }
    }

    @ApiOperation(value = "유저 정보 수정")
    @PutMapping("/user/{user_no}")
    public boolean update(@PathVariable Long user_no, @RequestBody UserUpdateRequestDto userSaveRequestDto){
        return userService.update(user_no, userSaveRequestDto);
    }

    @ApiOperation(value = "유저 정보 삭제")
    @DeleteMapping("/user/{user_no}")
    public boolean deleteUser(@PathVariable Long user_no) {
        return userService.deleteUser(user_no);
    }

    @ApiOperation(value = "로그인", notes = "구글 로그인 페이지로 이동")
    @GetMapping("/user/login")
    public void login(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("/oauth2/authorization/google");
    }

    @ApiOperation(value = "로그아웃", notes = "로그아웃 후 홈 페이지로 이동")
    @GetMapping("/user/logout")
    public void logout(HttpServletResponse httpServletResponse) throws IOException {
        httpSession.invalidate();
        httpServletResponse.sendRedirect("/");
    }

    @ApiOperation(value = "로그인 유무", notes = "로그인 한 경우, true 반환 | 로그인 하지 않은 경우, false 반환")
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