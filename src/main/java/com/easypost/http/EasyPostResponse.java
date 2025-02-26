package com.easypost.http;

import lombok.Getter;

@Getter
public class EasyPostResponse {
    private int responseCode;
    private String responseBody;

    /**
     * Constructor.
     *
     * @param responseCode HTTP response code
     * @param responseBody HTTP response body
     */
    public EasyPostResponse(final int responseCode, final String responseBody) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }
}
