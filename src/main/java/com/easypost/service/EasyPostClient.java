package com.easypost.service;

import com.easypost.http.Constant;

public class EasyPostClient {
    private String apiKey;
    private int connectTimeoutMilliseconds = Constant.DEFAULT_CONNECT_TIMEOUT_MILLISECONDS;
    private int readTimeoutMilliseconds = Constant.DEFAULT_READ_TIMEOUT_MILLISECONDS;

    public EasyPostClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds) {
        this.apiKey = apiKey;
        this.connectTimeoutMilliseconds = connectTimeoutMilliseconds;
    }

    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds, int readTimeoutMilliseconds) {
        this.apiKey = apiKey;
        this.connectTimeoutMilliseconds = connectTimeoutMilliseconds;
        this.readTimeoutMilliseconds = readTimeoutMilliseconds;
    }

    public int getConnectionTimeout() {
        return connectTimeoutMilliseconds;
    }

    public int getReadtimeout() {
        return readTimeoutMilliseconds;
    }

    public String getApiKey() {
        return apiKey;
    }

    public AddressService Address() {
        return new AddressService(this);
    }
}
