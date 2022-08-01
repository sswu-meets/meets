package com.sswu.meets.domain.avTime;

import com.sswu.meets.domain.dateTune.DateTune;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AvTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avTimeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date_tune_no")
    private DateTune dateTune;

    private LocalTime avTime;

    @Builder
    public AvTime(DateTune dateTune, LocalTime avTime) {
        this.dateTune = dateTune;
        this.avTime = avTime;
    }

}
