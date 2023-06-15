package com.easypost.mocking.classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MockSmartRateResponse {
    @SerializedName ("result")
    private List<MockSmartRate> rates;

    public MockSmartRateResponse(List<MockSmartRate> rates) {
        this.rates = rates;
    }
}
