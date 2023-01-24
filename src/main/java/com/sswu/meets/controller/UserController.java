package com.sswu.meets.controller;

import com.sswu.meets.config.auth.LoginUser;
import com.sswu.meets.config.auth.dto.SessionUser;
import com.sswu.meets.domain.user.User;
import com.sswu.meets.domain.user.UserRepository;
import com.sswu.meets.dto.*;
import com.sswu.meets.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "유저")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;
    private final UserRepository userRepository;

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

    @ApiOperation(value = "유저가 참여하고 있는 모든 모임 조회")
    @GetMapping("/user/meetinglist/{userNo}")
    public List<MeetingResponseDto> getMeetingListOfUser(@PathVariable Long userNo) {
        return userService.getMeetingList(userNo);
    }

    @ApiOperation(value = "유저가 참여하고 있는 일정 조회")
    @GetMapping("/user/schedulelist/{userNo}")
    public List<ScheduleResponseDto> getScheduleListOfUser(@PathVariable Long userNo) {
        return userService.getScheduleList(userNo);
    }

    @ApiOperation(value = "유저 정보 조회", notes = "넘겨준 유저 고유 번호에 해당하는 유저가 없을 때는 에러 메세지 반환")
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = User.class),
            @ApiResponse(code = 400, message = "해당 유저는 존재하지 않습니다.")
    })
    @GetMapping("/user/{userNo}")
    public ResponseEntity getUserInfo(@PathVariable Long userNo) {
        try {
            User user = userService.getUser(userNo);
            return ResponseEntity.status(200).body(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @ApiOperation(value = "유저 정보 수정")
    @PutMapping("/user/{userNo}")
    public Boolean update(@PathVariable Long userNo, @RequestBody UserUpdateRequestDto userSaveRequestDto){
        return userService.update(userNo, userSaveRequestDto);
    }

    @ApiOperation(value = "탈퇴하기")
    @DeleteMapping("/user/{userNo}")
    public Boolean deleteUser(@PathVariable Long userNo) {
        httpSession.invalidate();
        return userService.deleteUser(userNo);
    }

    @ApiOperation(value = "로그인", notes = "유저 정보 반환")
    @PostMapping("/user/login")
    public User login(@Valid @RequestBody GoogleLoginRequestDto googleLoginRequestDto){
        return userService.login(googleLoginRequestDto);
    }

    @ApiOperation(value = "로그아웃", notes = "로그아웃 후 홈 페이지로 이동")
    @GetMapping("/user/logout")
    public Boolean logout() {
        httpSession.invalidate();
        return true;
    }

    @ApiOperation(value = "로그인 유무", notes = "로그인 한 경우, true 반환 | 로그인 하지 않은 경우, false 반환")
    @GetMapping("/user/status")
    public Boolean getUserStatus(@PathVariable Long userNo) {
        User user = userRepository.getById(userNo);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
}