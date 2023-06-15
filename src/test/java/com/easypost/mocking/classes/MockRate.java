package com.easypost.mocking.classes;

import com.google.gson.annotations.SerializedName;

public class MockRate {
    @SerializedName ("rate")
    private String rate;
    @SerializedName("carrier")
    private String carrier;
    @SerializedName("service")
    private String service;

    public MockRate(String price, String carrier, String service) {
        this.rate = price;
        this.carrier = carrier;
        this.service = service;
    }
}
