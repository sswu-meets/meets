package com.sswu.meets.service;

import com.sswu.meets.domain.schedule.Schedule;
import com.sswu.meets.domain.schedule.ScheduleRepository;
import com.sswu.meets.domain.tuneTime.TuneTime;
import com.sswu.meets.domain.tuneTime.TuneTimeRepository;
import com.sswu.meets.dto.Best3ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TuneTimeService {

    private final TuneTimeRepository tuneTimeRepository;
    private final ScheduleRepository scheduleRepository;

    /*
    * Best3에 저장된 객체가 없을 때는 추가
    * 마지막 객체의 종료시간과 현재 객체의 시작시간이 같으면, 마지막 객체의 종료시간을 30분 연장. 다르면 새로 추가.
    */
    @Transactional
    public List<Best3ResponseDto> calculateBestTime(Long scheduleNo) {
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정은 존재하지 않습니다. scheduleNo=" + scheduleNo));

        List<Best3ResponseDto> best3result = new ArrayList<>();
        for (TuneTime tuneTimeListItem : tuneTimeRepository.findAllByScheduleOrderByAvPeopleNoDescTuneTime(schedule)) {

            if (best3result.size() == 3) {
                break;
            }

            if (best3result.isEmpty()) {
                Best3ResponseDto best3ResponseDto = new Best3ResponseDto();
                best3result.add(best3ResponseDto.toBest3Item(tuneTimeListItem));
            } else {
                int lastIndex = best3result.size() - 1;
                Best3ResponseDto best3resultLastItem = best3result.get(lastIndex);

                if (tuneTimeListItem.getTuneTime().equals(best3resultLastItem.getEndTime())) {
                    best3resultLastItem.updateEndTime(tuneTimeListItem.getTuneTime().plusMinutes(30));
                } else {
                    Best3ResponseDto best3ResponseDto = new Best3ResponseDto();
                    best3result.add(best3ResponseDto.toBest3Item(tuneTimeListItem));
                }
            }
        }
        return best3result;
    }

}
