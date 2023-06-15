package com.easypost.mocking.classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MockStatelessRateResponse {
    @SerializedName ("rates")
    private List<MockRate> rates;

    /**
     * Construct a new MockStatelessRateResponse to serialize into JSON.
     * @param rates The MockRates to use.
     */
    public MockStatelessRateResponse(List<MockRate> rates) {
        this.rates = rates;
    }
}
