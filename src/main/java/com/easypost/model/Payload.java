package com.easypost.model;

import java.util.Map;

import lombok.Getter;

@Getter
public class Payload extends EasyPostResource {
    private int responseCode;
    private int totalTime;
    private Map<String, String> requestHeaders;
    private Map<String, String> responseHeaders;
    private String requestBody;
    private String requestUrl;
    private String responseBody;
}
