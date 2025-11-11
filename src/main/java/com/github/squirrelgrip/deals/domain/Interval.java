package com.github.squirrelgrip.deals.domain;

import java.time.LocalTime;

public class Interval {
    private final LocalTime timeStart;
    private final LocalTime timeEnd;
    private final Integer count;

    public Interval(
            LocalTime timeStart,
            LocalTime timeEnd,
            Integer count
    ) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.count = count;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public Integer getCount() {
        return count;
    }
}
