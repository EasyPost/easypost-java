package com.easypost.model;

import java.util.ArrayList;
import java.util.List;

public final class SmartRateCollection {
    private List<SmartRate> smartRates;

    /**
     * Get this SmartRateCollection's Smartrate objects.
     *
     * @return List of Smartrate objects.
     */
    public List<SmartRate> getSmartRates() {
        return this.smartRates;
    }

    /**
     * Set this SmartRateCollection's Smartrate objects.
     *
     * @param smartRates List of Smartrate objects.
     */
    public void setSmartrates(final List<SmartRate> smartRates) {
        this.smartRates = smartRates;
    }

    /**
     * Constructor.
     */
    public SmartRateCollection() {
        this.smartRates = new ArrayList<SmartRate>();
    }

    /**
     * Create a SmartRateCollection from a list of rates.
     *
     * @param smartRates List of Smartrate objects
     */
    public SmartRateCollection(final List<SmartRate> smartRates) {
        setSmartrates(smartRates);
    }

    /**
     * Add a SmartRate object to this SmartRateCollection.
     *
     * @param rate Rate object
     */
    public void addRate(final SmartRate rate) {
        smartRates.add(rate);
    }
}
