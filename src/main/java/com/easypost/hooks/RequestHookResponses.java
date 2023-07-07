package com.easypost.hooks;

import java.util.Map;

import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class RequestHookResponses {
    private Map<String, String> headers;
    private String method;
    private String path;
    private JsonObject requestBody;
    private String requestTimestamp;
    private String requestUuid;

    /**
     * RequestHookResponses constructor.
     *
     * @param headers The headers of the request.
     * @param method The HTTP method of the request.
     * @param path The path of the request.
     * @param requestBody The JSON object representing the request body.
     * @param requestTimestamp The timestamp of the request.
     * @param requestUuid The UUID of the request.
     */
    public RequestHookResponses(Map<String, String> headers, String method, String path, JsonObject requestBody,
                                String requestTimestamp, String requestUuid) {
        this.headers = headers;
        this.method = method;
        this.path = path;
        this.requestBody = requestBody;
        this.requestTimestamp = requestTimestamp;
        this.requestUuid = requestUuid;
    }
}
