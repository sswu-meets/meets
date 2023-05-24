package com.sswu.meets.service;

import com.sswu.meets.config.auth.dto.SessionUser;
import com.sswu.meets.domain.attendance.Attendance;
import com.sswu.meets.domain.attendance.AttendanceRepository;
import com.sswu.meets.domain.meeting.Meeting;
import com.sswu.meets.domain.meeting.MeetingRepository;
import com.sswu.meets.domain.schedule.DateTuneType;
import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.schedule.ScheduleRepository;
import com.sswu.meets.domain.scheduleDateFix.ScheduleDateFix;
import com.sswu.meets.domain.scheduleDateFix.ScheduleDateFixRepository;
import com.sswu.meets.domain.scheduleDateTune.ScheduleDateTuneRepository;
import com.sswu.meets.domain.user.UserRepository;
import com.sswu.meets.dto.UserResponseDto;
import com.sswu.meets.dto.schedule.request.FixScheduleSaveRequestDto;
import com.sswu.meets.dto.schedule.request.ScheduleDateTuneSaveRequestDto;
import com.sswu.meets.dto.schedule.request.ScheduleUpdateRequestDto;
import com.sswu.meets.dto.schedule.request.TuneRangeScheduleSaveRequestDto;
import com.sswu.meets.dto.schedule.response.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;
    private final AttendanceRepository attendanceRepository;
    private final ScheduleDateFixRepository scheduleDateFixRepository;
    private final ScheduleDateTuneRepository scheduleDateTuneRepository;

    // 고정 일정 등록
    @Transactional
    public Long saveFixDate(SessionUser sessionUser, FixScheduleSaveRequestDto fixRequestDto) {
        Meeting meeting = fixRequestDto.getMeetingNo().isPresent()
                ? meetingRepository.getById(fixRequestDto.getMeetingNo().getAsLong())
                : null;
        Schedule schedule = fixRequestDto.toSchedule(meeting, DateTuneType.FIX);
        scheduleRepository.save(schedule);
        scheduleDateFixRepository.save(fixRequestDto.toScheduleDateFix(schedule));
        attendanceRepository.save(Attendance.builder()
                .schedule(schedule)
                .user(userRepository.getById(sessionUser.getUserNo()))
                .build());

        return schedule.getScheduleNo();
    }

    //  조율 일정(기간) 등록
    @Transactional
    public Long saveTuneRangeSchedule(SessionUser sessionUser, TuneRangeScheduleSaveRequestDto tuneRequestDto) {
        Meeting meeting = tuneRequestDto.getMeetingNo().isPresent()
                ? meetingRepository.getById(tuneRequestDto.getMeetingNo().getAsLong())
                : null;
        Schedule schedule = tuneRequestDto.toSchedule(meeting, DateTuneType.RANGE);
        scheduleRepository.save(schedule).getScheduleNo();
        tuneRequestDto.getStartDate().datesUntil(tuneRequestDto.getEndDate())
                .forEach(d -> scheduleDateTuneRepository.save(tuneRequestDto.toScheduleDateTune(schedule, d)));
        attendanceRepository.save(Attendance.builder()
                        .schedule(schedule)
                        .user(userRepository.getById(sessionUser.getUserNo()))
                        .build());

        return schedule.getScheduleNo();
    }

    // 일정에 참여하는 유저 조회
    public List<String> getUserNameListBySchedule(Schedule schedule) {
        return attendanceRepository.findAttendanceBySchedule(schedule).stream()
                .map(p -> p.getUser().getName())
                .collect(Collectors.toList());
    }

    // 일정 번호로 일정에 참여하는 유저 조회
    public List<UserResponseDto> getUserListByScheduleNo(Long scheduleNo) {
        Schedule schedule = scheduleRepository.getById(scheduleNo);
        return attendanceRepository.findAttendanceBySchedule(schedule).stream()
                .map(p -> p.getUser())
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    // 모임에 속한 일정 조회
    @Transactional
    public List<ScheduleResponseDto> getScheduleList(Long meetingNo) {
        Meeting meeting = meetingRepository.getById(meetingNo);

        return scheduleRepository.findByMeeting(meeting).stream()
                .map(this::getSchedule)
                .collect(Collectors.toList());
    }

    // 일정 상세 조회
    @Transactional
    public ScheduleResponseDto getSchedule(Long scheduleNo, String scheduleCode) {
        return scheduleRepository.findByScheduleNoAndScheduleCode(scheduleNo, scheduleCode)
                .map(this::getSchedule)
                .orElseThrow(() -> new IllegalArgumentException(
                        "해당 일정은 존재하지 않습니다. scheduleNo: " + scheduleNo + ", scheduleCode: " + scheduleCode
                ));
    }

    // 일정 상세 조회
    @Transactional
    public ScheduleResponseDto getSchedule(Schedule schedule) {
        // 조율 일정인 경우
        if (schedule.getDateTuneState()) {
            List<LocalDate> tuneDateList = scheduleDateTuneRepository.findBySchedule(schedule).stream()
                    .map(s -> s.getTuneDate())
                    .collect(Collectors.toList());

            return ScheduleResponseDto.builder()
                    .schedule(schedule)
                    .tuneDateList(tuneDateList)
                    .build();
        }

        // 고정 일정인 경우
        ScheduleDateFix scheduleDateFix = scheduleDateFixRepository.findBySchedule(schedule);
        return ScheduleResponseDto.builder()
                .schedule(schedule)
                .fixStartDate(scheduleDateFix.getStartDate())
                .fixEndDate(scheduleDateFix.getEndDate())
                .build();
    }

    // 일정 수정
    @Transactional
    public Boolean update(Long scheduleNo, ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정은 존재하지 않습니다. scheduleNo: " + scheduleNo));
        schedule.update(scheduleUpdateRequestDto.getScheduleName());

        return true;
    }

    // 일정 삭제
    public Boolean delete(Long scheduleNo) {
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정은 존재하지 않습니다. scheduleNo: " + scheduleNo));
        scheduleRepository.delete(schedule);

        return true;
    }

}


