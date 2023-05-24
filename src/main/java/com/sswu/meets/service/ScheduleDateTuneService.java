package com.sswu.meets.service;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.schedule.ScheduleRepository;
import com.sswu.meets.domain.scheduleDateTune.ScheduleDateTuneRepository;
import com.sswu.meets.dto.schedule.request.ScheduleDateTuneSaveRequestDto;
import com.sswu.meets.dto.schedule.response.ScheduleDateTuneResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ScheduleDateTuneService {
    private final ScheduleDateTuneRepository scheduleDateTuneRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long saveTuneDate(Long scheduleNo, ScheduleDateTuneSaveRequestDto requestDto) {
        Schedule schedule = scheduleRepository.getById(scheduleNo);
        return scheduleDateTuneRepository.save(requestDto.toScheduleDateTune(schedule)).getScheduleDateTuneNo();
    }

    @Transactional
    public List<ScheduleDateTuneResponseDto> getDateTuneList(Long scheduleNo) {
        Schedule schedule = scheduleRepository.getById(scheduleNo);
        return scheduleDateTuneRepository.findBySchedule(schedule).stream()
                .map(ScheduleDateTuneResponseDto::new)
                .collect(Collectors.toList());
    }
}
