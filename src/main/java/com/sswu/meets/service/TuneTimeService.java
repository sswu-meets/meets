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

    @Transactional
    public List<Best3ResponseDto> calculateBestTime(Long scheduleNo) {
        Schedule schedule = scheduleRepository.findById(scheduleNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정은 없습니다. scheduleNo=" + scheduleNo));

        List<Best3ResponseDto> best3result = new ArrayList<>();
        for (TuneTime tuneTimeListItem : tuneTimeRepository.findAllByScheduleOrderByAvPeopleNoDescTuneTime(schedule)) {

            log.info("best3size: {}개", best3result.size());

            if (best3result.size() == 3) {
                break;
            }

            if (best3result.isEmpty()) { // 저장된 Best3 아이템이 없을 때는 추가
                Best3ResponseDto best3ResponseDto = new Best3ResponseDto();
                best3result.add(best3ResponseDto.toBest3Item(tuneTimeListItem));
                log.info("Best3-{} 추가(39):{} | {} ~ {}", best3result.size(), tuneTimeListItem.getTuneDate(), tuneTimeListItem.getTuneTime(), tuneTimeListItem.getTuneTime().plusMinutes(30));
            } else { // (저장된 아이템이 있는 경우) 전 아이템의 endTime과 현재 아이템의 startTime이 같으면 전 아이템의 endTime을 30분 연장, 다르면 새로 추가
                int lastIndex = best3result.size() - 1;
                Best3ResponseDto best3resultLastItem = best3result.get(lastIndex);

                if (tuneTimeListItem.getTuneTime().equals(best3resultLastItem.getEndTime())) {
                    best3resultLastItem.updateEndTime(tuneTimeListItem.getTuneTime().plusMinutes(30));
                    log.info("Best3-{} 수정:{} | {} ~ {}", best3result.size(), best3resultLastItem.getBestDate(), best3resultLastItem.getStartTime(), best3resultLastItem.getEndTime());
                } else {
                    Best3ResponseDto best3ResponseDto = new Best3ResponseDto();
                    best3result.add(best3ResponseDto.toBest3Item(tuneTimeListItem));
                    log.info("Best3-{} 추가(50):{} | {} ~ {}", best3result.size(), tuneTimeListItem.getTuneDate(), tuneTimeListItem.getTuneTime(), tuneTimeListItem.getTuneTime().plusMinutes(30));
                }
            }
            log.info("==============================================================");
        }
        return best3result;
    }

}
