package com.easypost.mocking.classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MockShipmentWithRatesResponse {
    @SerializedName ("rates")
    private List<MockRate> rates;

    public MockShipmentWithRatesResponse(List<MockRate> rates) {
        this.rates = rates;
    }
}
