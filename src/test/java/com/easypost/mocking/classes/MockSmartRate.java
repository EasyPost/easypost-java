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

    /**
     * Construct a new MockSmartRate to serialize into JSON.
     * @param price The price to use.
     * @param carrier The carrier to use.
     * @param service The service to use.
     * @param timeInTransit The MockTimeInTransit to use.
     */
    public MockSmartRate(String price, String carrier, String service, MockTimeInTransit timeInTransit) {
        this.rate = price;
        this.carrier = carrier;
        this.service = service;
        this.timeInTransit = timeInTransit;
    }
}
