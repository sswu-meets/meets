package com.sswu.meets.domain.avTime;

import com.sswu.meets.domain.dateTune.DateTune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvTimeRepository extends JpaRepository<AvTime, Long> {
    List<AvTime> findByDateTune(DateTune dateTune);
}
