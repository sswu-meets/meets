package com.sswu.meets.service;

import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.meeting.MeetingRepository;
import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.schedule.ScheduleRepository;
import com.sswu.meets.domain.user.User;
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

    // 일정 등록
    @Transactional
    public Long save(Long meetingNo, ScheduleSaveRequestDto scheduleSaveRequestDto) {
        Meeting meeting = meetingRepository.getById(meetingNo);

        return scheduleRepository.save(scheduleSaveRequestDto.toEntity(meeting)).getNo();
    }

    // 일정 조회
    @Transactional
    public List<ScheduleResponseDto> getScheduleList(Long meetingNo) {
        Meeting meeting = meetingRepository.getById(meetingNo);

        return scheduleRepository.findByMeeting(meeting).stream()
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


