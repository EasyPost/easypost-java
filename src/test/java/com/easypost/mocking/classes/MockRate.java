package com.easypost.mocking.classes;

import com.google.gson.annotations.SerializedName;

public class MockRate {
    @SerializedName ("rate")
    private String rate;
    @SerializedName("carrier")
    private String carrier;
    @SerializedName("service")
    private String service;

    /**
     * Construct a new MockRate to serialize into JSON.
     * @param price The price to use.
     * @param carrier The carrier to use.
     * @param service The service to use.
     */
    public MockRate(String price, String carrier, String service) {
        this.rate = price;
        this.carrier = carrier;
        this.service = service;
    }
}
