package com.github.squirrelgrip.deals.domain;

import java.time.LocalTime;

public class Peak {
    private final LocalTime peakTimeStart;
    private final LocalTime peakTimeEnd;

    public Peak(
            LocalTime peakTimeStart,
            LocalTime peakTimeEnd
    ) {
        this.peakTimeStart = peakTimeStart;
        this.peakTimeEnd = peakTimeEnd;
    }

    public LocalTime getPeakTimeStart() {
        return peakTimeStart;
    }

    public LocalTime getPeakTimeEnd() {
        return peakTimeEnd;
    }
}
