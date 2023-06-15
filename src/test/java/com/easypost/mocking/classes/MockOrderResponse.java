package com.easypost.mocking.classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MockOrderResponse {
    @SerializedName ("rates")
    private List<MockRate> rates;

    /**
     * Construct a new MockOrderResponse to serialize into JSON.
     * @param rates The MockRates to use.
     */
    public MockOrderResponse(List<MockRate> rates) {
        this.rates = rates;
    }
}
