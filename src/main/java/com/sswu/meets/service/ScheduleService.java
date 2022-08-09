package com.sswu.meets.service;

import com.sswu.meets.domain.attendance.AttendanceRepository;
import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.meeting.MeetingRepository;
import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.schedule.ScheduleRepository;
import com.sswu.meets.domain.user.User;
import com.sswu.meets.domain.user.UserRepository;
import com.sswu.meets.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;
    private final ScheduleDateTuneService scheduleDateTuneService;
    private final ScheduleDateFixService scheduleDateFixService;

    // 고정 일정 등록
    @Transactional
    public Long saveFixDate(Long meetingNo, FixScheduleSaveRequestDto fixRequestDto) {
        Meeting meeting = meetingRepository.getById(meetingNo);

        Long scheduleNo = scheduleRepository.save(fixRequestDto.toEntity(meeting)).getScheduleNo();

        ScheduleDateFixSaveRequestDto dateFixSaveRequestDto = fixRequestDto.changeFormat();

        scheduleDateFixService.saveFixDate(scheduleNo, dateFixSaveRequestDto);

        return scheduleNo;
    }

    //  조율 일정 등록
    @Transactional
    public Long saveTuneDate(Long meetingNo, TuneScheduleSaveRequestDto tuneRequestDto) {
        Meeting meeting = meetingRepository.getById(meetingNo);

        Long scheduleNo = scheduleRepository.save(tuneRequestDto.toEntity(meeting)).getScheduleNo();

        ScheduleDateTuneSaveRequestDto dateTuneSaveRequestDto = tuneRequestDto.changeFormat();

        scheduleDateTuneService.saveTuneDate(scheduleNo, dateTuneSaveRequestDto);

        return scheduleNo;
    }

    // 모임에 속한 일정 조회
    @Transactional
    public List<ScheduleResponseDto> getScheduleList(Long meetingNo) {
        Meeting meeting = meetingRepository.getById(meetingNo);

        return scheduleRepository.findByMeeting(meeting).stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    // 특정 일정 조회
    @Transactional
    public List<ScheduleResponseDto> getSchedule(Long scheduleNo) {
        return scheduleRepository.findById(scheduleNo).stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    // 유저가 참여하고 있는 일정 조회
    @Transactional
    public List<ScheduleResponseDto> getScheduleListOfUser(Long user_no) {
        User scheduleAttendance = userRepository.getById(user_no);
        return attendanceRepository.findAttendanceByUser(scheduleAttendance).stream()
                .map(p -> p.getSchedule())
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    // 일정 수정
    @Transactional
    public Boolean update(Long scheduleNo, ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다. scheduleNo=" + scheduleNo));
        schedule.update(scheduleUpdateRequestDto.getScheduleName());

        return true;
    }

    // 일정 삭제
    public Boolean delete(Long scheduleNo) {
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정 없습니다. scheduleNo=" + scheduleNo));
        scheduleRepository.delete(schedule);

        return true;
    }

}


