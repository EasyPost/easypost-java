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

    public SmartrateCollection(final List<Rate> rates) {
        this.rates = rates;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void addRate(final Rate rate) {
        rates.add(rate);
    }
}
