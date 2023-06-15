package com.easypost.mocking.classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MockShipmentWithRatesResponse {
    @SerializedName ("rates")
    private List<MockRate> rates;

    /**
     * Construct a new MockShipmentWithRatesResponse to serialize into JSON.
     * @param rates The MockRates to use.
     */
    public MockShipmentWithRatesResponse(List<MockRate> rates) {
        this.rates = rates;
    }
}
