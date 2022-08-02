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
    public Boolean saveAvDateAndTime(Long userNo, Long scheduleNo, List<DateTuneSaveRequestDto> requestDtoList) {
        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다. userNo=" + userNo));
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정은 없습니다. scheduleNo=" + scheduleNo));

        for (DateTuneSaveRequestDto requestDtoListItem : requestDtoList) {
            DateTune dateTune = requestDtoListItem.toEntity(user, schedule);

            deleteAvDateAndTime(dateTune);

            dateTune = dateTuneRepository.save(dateTune);
            log.info("가능 날짜 저장 완료");

            for (String avTimeListItem : requestDtoListItem.getAvTime()) {
                AvTime avTime = requestDtoListItem.toAvTime(dateTune, avTimeListItem);
                avTime = avTimeRepository.save(avTime);
                log.info("가능 시간 저장 완료");

                TuneTime tuneTime = tuneTimeRepository.findByScheduleAndTuneDateAndTuneTime(schedule, dateTune.getAvDate(), avTime.getAvTime());

                if (tuneTime == null) {
                    tuneTimeRepository.save(requestDtoListItem.toTuneTime(schedule, avTimeListItem, 1L));
                    log.info("일정 조율 시간에 새로운 데이터 저장");
                } else {
                    tuneTime.addPeopleNo();
                    log.info("일정 조율 시간에 가능 인원 수 증가");
                }
            }
        }



        return true;
    }

    // 기존 조율 데이터가 있는 경우, 수정을 위해 삭제
    public void deleteAvDateAndTime(DateTune requestDateTune) {
        DateTune dateTune = dateTuneRepository.findByUserAndScheduleAndAvDate(requestDateTune.getUser(), requestDateTune.getSchedule(), requestDateTune.getAvDate());

        if (dateTune != null) {
            log.info("기존 조율 데이터를 삭제합니다.");
            dateTuneRepository.delete(dateTune);
            List<AvTime> avTimeList = avTimeRepository.findByDateTune(dateTune);
            for (AvTime avTimeListItem : avTimeList) {
                log.info("avTimeNo={}", avTimeListItem.getAvTimeNo());
                avTimeRepository.delete(avTimeListItem);
                log.info("scheduleNo={}", requestDateTune.getSchedule().getScheduleNo());
                log.info("userNo={}", requestDateTune.getUser().getUserNo());
                TuneTime tuneTime = tuneTimeRepository.findByScheduleAndTuneDateAndTuneTime(requestDateTune.getSchedule(), dateTune.getAvDate(), avTimeListItem.getAvTime());
                log.info("tuneTime.subPeoleNo={}", tuneTime.subPeopleNo());
                if (tuneTime.subPeopleNo() == 0) {
                    tuneTimeRepository.delete(tuneTime);
                }
            }

        } else {
            log.info("새로운 조율 데이터 저장을 시작합니다.");
        }
    }
}
