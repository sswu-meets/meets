package com.sswu.meets.domain.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DateTuneType {
    FIX(false),
    RANGE(true),
    MULTIPLE_SELECTION(true);
    private final boolean status;
}
