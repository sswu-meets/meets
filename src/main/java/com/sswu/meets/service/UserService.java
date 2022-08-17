package com.sswu.meets.service;

import com.sswu.meets.domain.attendance.AttendanceRepository;
import com.sswu.meets.domain.participation.ParticipationRepository;
import com.sswu.meets.domain.user.User;
import com.sswu.meets.domain.user.UserRepository;
import com.sswu.meets.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;
    private final AttendanceRepository attendanceRepository;

    @Transactional
    public Long save(UserSaveRequestDto userSaveRequestDto) {
        return userRepository.save(userSaveRequestDto.toEntity()).getUserNo();
    }

    // 유저 조회
    @Transactional
    public User getUser(Long userNo) {
        return userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다. scheduleNo=" + userNo));
    }

    // 결과로 넘어온 User의 Stream을 map을 통해 UserResponseDto 변환, 그 후 List로 반환
    @Transactional
    public List<UserResponseDto> getUserList() {
        System.out.println(userRepository.findAll());
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    // 유저가 참여하고 있는 모임 조회
    @Transactional
    public List<MeetingResponseDto> getMeetingList(Long userNo) {
        User participationUser = userRepository.getById(userNo);

        return participationRepository.findParticipationByUser(participationUser).stream()
                .map(p -> p.getMeeting())
                .map(MeetingResponseDto::new)
                .collect(Collectors.toList());
    }

    // 유저가 참여하고 있는 일정 조회
    @Transactional
    public List<ScheduleResponseDto> getScheduleList(Long userNo) {
        User userAttendance = userRepository.getById(userNo);
        return attendanceRepository.findAttendanceByUser(userAttendance).stream()
                .map(p -> p.getSchedule())
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean update(Long userNo, UserUpdateRequestDto userSaveRequestDto) {
        try{
            userRepository.save(userSaveRequestDto.toEntity());
            return true;
        }catch (IllegalArgumentException e){
            System.out.println("error: " + e);
            return false;
        }
    }

    @Transactional
    public boolean deleteUser(Long userNo) {
        User user = userRepository.getById(userNo);  //getById:프록시만 반환
        try {
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e);
            return false;
        }
    }

    @Transactional
    public User login(UserSaveRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .map(entity -> entity.update(requestDto.getName(), requestDto.getProfileUrl()))
                .orElse(requestDto.toEntity());

        return userRepository.save(user);
    }
}
