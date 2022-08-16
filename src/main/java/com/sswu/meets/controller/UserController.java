package com.sswu.meets.controller;

import com.sswu.meets.config.auth.LoginUser;
import com.sswu.meets.config.auth.dto.SessionUser;
import com.sswu.meets.domain.user.User;
import com.sswu.meets.dto.MeetingResponseDto;
import com.sswu.meets.dto.UserResponseDto;
import com.sswu.meets.dto.UserSaveRequestDto;
import com.sswu.meets.dto.UserUpdateRequestDto;
import com.sswu.meets.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@Api(tags = "유저")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;

    @ApiOperation(value = "홈 api", notes = "로그인 한 유저의 경우, OO님 환영합니다. | 로그인 하지 않은 유저의 경우, \"meets에 오신 걸 환영합니다:)\"")
    @GetMapping("/")
    public String hello(@LoginUser SessionUser user) {
        if (user != null) {
            String userName = user.getName();
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
    @GetMapping("/user/all")
    public List<UserResponseDto> getUserList() {
        return userService.getUserList();
    }

    @ApiOperation(value = "유저 정보 조회", notes = "\"로그인 한 유저의 경우, 유저 정보 반환 | 로그인 하지 않은 유저의 경우, \"로그인을 먼저 해주세요.\" 안내 메세지 반환")
    @GetMapping("/user")
    public ResponseEntity getUserInfo(@LoginUser SessionUser user) {
        if (user != null) {
            return ResponseEntity.status(200).body(user);
        } else {
            return ResponseEntity.status(401).body("SessionUser is null");
        }
    }

    @ApiOperation(value = "유저가 참여하고 있는 모든 모임 조회")
    @GetMapping("/user/meetinglist")
    public List<MeetingResponseDto> getMeetingListOfUser(@LoginUser SessionUser user) {
        return userService.getMeetingList(user.getUserNo());
    }

    @ApiOperation(value = "유저 정보 수정")
    @PutMapping("/user")
    public Boolean update(@LoginUser SessionUser user, @RequestBody UserUpdateRequestDto userSaveRequestDto){
        return userService.update(user.getUserNo(), userSaveRequestDto);
    }

    @ApiOperation(value = "탈퇴하기")
    @DeleteMapping("/user")
    public Boolean deleteUser(@LoginUser SessionUser user) {
        httpSession.invalidate();
        return userService.deleteUser(user.getUserNo());
    }

    @ApiOperation(value = "로그인", notes = "유저 정보 반환")
    @PostMapping("/user/login")
    public User login(@Valid @RequestBody UserSaveRequestDto requestDto){
        return userService.login(requestDto);
    }

    @ApiOperation(value = "로그아웃", notes = "로그아웃 후 홈 페이지로 이동")
    @GetMapping("/user/logout")
    public Boolean logout() {
        httpSession.invalidate();
        return true;
    }

    @ApiOperation(value = "로그인 유무", notes = "로그인 한 경우, true 반환 | 로그인 하지 않은 경우, false 반환")
    @GetMapping("/user/status")
    public Boolean getUserStatus(@LoginUser SessionUser user) {
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
}