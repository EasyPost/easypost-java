package com.easypost.hooks;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ResponseHookResponses {
    private int httpStatus;
    private Map<String, String> headers;
    private String method;
    private String path;
    private String responseBody;
    private String responseTimestamp;
    private String requestTimestamp;
    private String requestUuid;

    /**
     * ResponseHookResponses constructor.
     *
     * @param httpStatus The HTTP status code of the response.
     * @param headers The headers of the response.
     * @param method The HTTP method of the request.
     * @param path The path of the request.
     * @param responseBody The response body as a string.
     * @param responseTimestamp The timestamp of the response.
     * @param requestTimestamp The timestamp of the corresponding request.
     * @param requestUuid The UUID of the corresponding request.
     */
    public ResponseHookResponses(int httpStatus, Map<String, String> headers, String method, String path,
        String responseBody, String responseTimestamp, String requestTimestamp, String requestUuid) {
        this.httpStatus = httpStatus;
        this.headers = headers;
        this.method = method;
        this.path = path;
        this.responseBody = responseBody;
        this.responseTimestamp = responseTimestamp;
        this.requestTimestamp = requestTimestamp;
        this.requestUuid = requestUuid;
    }
}
