/**
 * SmartrateCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import java.util.ArrayList;
import java.util.List;

public final class SmartrateCollection {
    private List<Rate> rates = new ArrayList<>();

    public SmartrateCollection() {
    }

    /**
     * Create a SmartrateCollection from a list of rates.
     *
     * @param rates List of Rate objects
     */
    public SmartrateCollection(final List<Rate> rates) {
        this.rates = rates;
    }

    /**
     * Get the Rate objects in this SmartrateCollection.
     *
     * @return List of Rate objects
     */
    public List<Rate> getRates() {
        return rates;
    }

    /**
     * Add a Rate object to this SmartrateCollection.
     *
     * @param rate Rate object
     */
    public void addRate(final Rate rate) {
        rates.add(rate);
    }
}
