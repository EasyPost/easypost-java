package com.easypost.mocking.classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MockOrderResponse {
    @SerializedName ("rates")
    private List<MockRate> rates;

    public MockOrderResponse(List<MockRate> rates) {
        this.rates = rates;
    }
}
