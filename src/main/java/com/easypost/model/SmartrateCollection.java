package com.easypost.model;

import java.util.ArrayList;
import java.util.List;

public final class SmartrateCollection {
    private List<SmartRate> smartrates;

    /**
     * Get this SmartrateCollection's Smartrate objects.
     *
     * @return List of Smartrate objects.
     */
    public List<SmartRate> getSmartrates() {
        return this.smartrates;
    }

    /**
     * Set this SmartrateCollection's Smartrate objects.
     *
     * @param smartrates List of Smartrate objects.
     */
    public void setSmartrates(final List<SmartRate> smartrates) {
        this.smartrates = smartrates;
    }

    /**
     * Constructor.
     */
    public SmartrateCollection() {
        this.smartrates = new ArrayList<SmartRate>();
    }

    /**
     * Create a SmartrateCollection from a list of rates.
     *
     * @param smartrates List of Smartrate objects
     */
    public SmartrateCollection(final List<SmartRate> smartrates) {
        setSmartrates(smartrates);
    }

    /**
     * Add a SmartRate object to this SmartrateCollection.
     *
     * @param rate Rate object
     */
    public void addRate(final SmartRate rate) {
        smartrates.add(rate);
    }
}
