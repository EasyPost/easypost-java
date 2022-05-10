package com.easypost.model;

public class Smartrate extends Rate {
    private TimeInTransit timeInTransit;

    /**
     * Get the time in transit for this rate.
     *
     * @return timeInTransit
     */
    public TimeInTransit getTimeInTransit() {
        return timeInTransit;
    }
}
