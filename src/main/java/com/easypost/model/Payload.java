package com.easypost.model;

import java.util.Map;

import lombok.Getter;

@Getter
public class Payload extends EasyPostResource {
    private String requestUrl;
    private Map<String, String> requestHeaders;
    private Map<String, Object> requestBody;
    private Map<String, String> responseHeaders;
    private String responseBody;
    private int responseCode;
    private int totalTime;
}
