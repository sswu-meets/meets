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

    //일정 등록
    @Transactional
    public Long save(Long meetingNo, ScheduleSaveRequestDto scheduleSaveRequestDto) {
        Meeting meeting = meetingRepository.getById(meetingNo);

        return scheduleRepository.save(scheduleSaveRequestDto.toEntity(meeting)).getNo();
    }

    //일정 조회
    @Transactional
    public List<ScheduleResponseDto> getScheduleList() {
        System.out.println(scheduleRepository.findAll());
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    //일정 수정
    @Transactional
    public Boolean update(Long schedule_no, ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        Schedule schedule = scheduleRepository.getById(schedule_no);
        try {
            schedule = scheduleUpdateRequestDto.toEntity();
            scheduleRepository.save(schedule);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("error: " + e);
            return false;
        }
    }
}

//    public Long update(Long schedule_no, ScheduleUpdateRequestDto requestDto) {
//        Schedule schedule = scheduleRepository.findById(schedule_no)
//                .orElseThrow(() -> new
//                IllegalArgumentException("해당 게시글이 없습니다. id=" + schedule_no));
//
//        schedule.update(requestDto.getSchedule_name(), requestDto.getDate_tune(),
//                requestDto.getTime_tune(), requestDto.getSchedule_memo());
//
//        return schedule_no;
//    }

