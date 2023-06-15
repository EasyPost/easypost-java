package com.easypost.mocking.classes;

import com.google.gson.annotations.SerializedName;

public class MockSmartRate {
    @SerializedName("carrier")
    private String carrier;
    @SerializedName("service")
    private String service;
    @SerializedName ("rate")
    private String rate;
    @SerializedName("time_in_transit")
    private MockTimeInTransit timeInTransit;

    public MockSmartRate(String price, String carrier, String service, MockTimeInTransit timeInTransit) {
        this.rate = price;
        this.carrier = carrier;
        this.service = service;
        this.timeInTransit = timeInTransit;
    }
}
