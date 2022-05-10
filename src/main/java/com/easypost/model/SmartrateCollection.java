package com.easypost.model;

import java.util.ArrayList;
import java.util.List;

public final class SmartrateCollection {

    private List<Smartrate> smartrates;

    /**
     * Constructor.
     */
    public SmartrateCollection() {
        this.smartrates = new ArrayList<Smartrate>();
    }

    /**
     * Create a SmartrateCollection from a list of rates.
     *
     * @param smartrates List of Smartrate objects
     */
    public SmartrateCollection(final List<Smartrate> smartrates) {
        setSmartrates(smartrates);
    }

    /**
     * Get this SmartrateCollection's Smartrate objects.
     *
     * @return List of Smartrate objects.
     */
    public List<Smartrate> getSmartrates() {
        return this.smartrates;
    }

    /**
     * Set this SmartrateCollection's Smartrate objects.
     *
     * @param smartrates List of Smartrate objects.
     */
    public void setSmartrates(final List<Smartrate> smartrates) {
        this.smartrates = smartrates;
    }

    /**
     * Add a SmartRate object to this SmartrateCollection.
     *
     * @param rate Rate object
     */
    public void addRate(final Smartrate rate) {
        smartrates.add(rate);
    }
}
