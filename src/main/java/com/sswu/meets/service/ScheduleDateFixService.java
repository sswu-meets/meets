package com.sswu.meets.service;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.schedule.ScheduleRepository;
import com.sswu.meets.domain.scheduleDateFix.ScheduleDateFixRepository;
import com.sswu.meets.dto.ScheduleDateFixSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ScheduleDateFixService {
    private final ScheduleDateFixRepository scheduleDateFixRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long saveFixDate(Long scheduleNo, ScheduleDateFixSaveRequestDto requestDto) {
        Schedule schedule = scheduleRepository.getById(scheduleNo);
        return scheduleDateFixRepository.save(requestDto.toEntity(schedule)).getScheduleDateFixNo();
    }
}
