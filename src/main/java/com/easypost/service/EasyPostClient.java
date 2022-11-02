package com.easypost.service;

import com.easypost.http.Constant;

public class EasyPostClient {
    private AddressService address;
    private String apiKey;
    private int connectTimeoutMilliseconds;
    private int readTimeoutMilliseconds;
    

    public EasyPostClient(String apiKey) {
        this(apiKey, Constant.DEFAULT_CONNECT_TIMEOUT_MILLISECONDS);
    }

    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds) {
        this(apiKey, Constant.DEFAULT_CONNECT_TIMEOUT_MILLISECONDS, Constant.DEFAULT_READ_TIMEOUT_MILLISECONDS);
    }

    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds, int readTimeoutMilliseconds) {
        this.apiKey = apiKey;
        this.connectTimeoutMilliseconds = connectTimeoutMilliseconds;
        this.readTimeoutMilliseconds = readTimeoutMilliseconds;

        address = new AddressService(this);
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
        return this.address;
    }

    public class beta {
        public void create() {

        }
    }
}
