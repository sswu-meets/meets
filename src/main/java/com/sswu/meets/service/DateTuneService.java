package com.sswu.meets.service;

import com.sswu.meets.domain.avTime.AvTime;
import com.sswu.meets.domain.avTime.AvTimeRepository;
import com.sswu.meets.domain.dateTune.DateTune;
import com.sswu.meets.domain.dateTune.DateTuneRepository;
import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.schedule.ScheduleRepository;
import com.sswu.meets.domain.tuneTime.TuneTime;
import com.sswu.meets.domain.tuneTime.TuneTimeRepository;
import com.sswu.meets.domain.user.User;
import com.sswu.meets.domain.user.UserRepository;
import com.sswu.meets.dto.DateTuneSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DateTuneService {
    private final DateTuneRepository dateTuneRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final AvTimeRepository avTimeRepository;
    private final TuneTimeRepository tuneTimeRepository;

    // 조율 데이터 저장
    @Transactional
    public Boolean saveAvDateAndTime(Long userNo, Long scheduleNo, DateTuneSaveRequestDto requestDto) {
        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다. userNo=" + userNo));
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정은 없습니다. scheduleNo=" + scheduleNo));

        deleteAvDateAndTime(user, schedule);

        DateTune dateTune = requestDto.toEntity(user, schedule);
        dateTune = dateTuneRepository.save(dateTune);
        log.info("가능 날짜 저장 완료");

        for (String avTimeListItem : requestDto.getAvTime()) {
            AvTime avTime = requestDto.toAvTime(dateTune, avTimeListItem);
            avTime = avTimeRepository.save(avTime);
            log.info("가능 시간 저장 완료");

            TuneTime tuneTime = tuneTimeRepository.findByScheduleAndTuneDateAndTuneTime(schedule, dateTune.getAvDate(), avTime.getAvTime());

            if (tuneTime == null) {
                tuneTimeRepository.save(requestDto.toTuneTime(schedule, avTimeListItem, 1L));
                log.info("일정 조율 시간에 새로운 데이터 저장");
            } else {
                tuneTime.addPeopleNo();
                log.info("일정 조율 시간에 가능 인원 수 증가");
            }
        }

        return true;
    }

    // 기존 조율 데이터가 있는 경우, 수정을 위해 삭제
    public void deleteAvDateAndTime(User user, Schedule schedule) {
        DateTune dateTune = dateTuneRepository.findByUserAndSchedule(user, schedule);

        if (dateTune != null) {
            dateTuneRepository.delete(dateTune);
            List<AvTime> avTimeList = avTimeRepository.findByDateTune(dateTune);
            for (AvTime avTimeListItem : avTimeList) {
                avTimeRepository.delete(avTimeListItem);
                TuneTime tuneTime = tuneTimeRepository.findByScheduleAndTuneDateAndTuneTime(schedule, dateTune.getAvDate(), avTimeListItem.getAvTime());
                if (tuneTime.subPeopleNo() == 0) {
                    tuneTimeRepository.delete(tuneTime);
                }
            }

            log.info("기존 조율 데이터가 삭제되었습니다.");
        } else {
            log.info("새로운 조율 데이터 저장을 시작합니다.");
        }
    }
}
