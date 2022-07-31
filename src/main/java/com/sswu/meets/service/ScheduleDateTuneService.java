package com.sswu.meets.service;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.schedule.ScheduleRepository;
import com.sswu.meets.domain.scheduleDateTune.ScheduleDateTuneRepository;
import com.sswu.meets.dto.ScheduleDateTuneSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ScheduleDateTuneService {
    private final ScheduleDateTuneRepository scheduleDateTuneRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long saveTuneDate(Long scheduleNo, ScheduleDateTuneSaveRequestDto requestDto) {
        Schedule schedule = scheduleRepository.getById(scheduleNo);
        return scheduleDateTuneRepository.save(requestDto.toEntity(schedule)).getScheduleDateTuneNo();
    }
}
