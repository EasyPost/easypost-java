package com.easypost.model;

import java.util.ArrayList;
import java.util.List;

public class SmartrateCollection {
    private List<Rate> rates = new ArrayList<>();

    public SmartrateCollection() { }

    public SmartrateCollection(List<Rate> rates) {
        this.rates = rates;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void addRate(Rate rate) {
        rates.add(rate);
    }
}
