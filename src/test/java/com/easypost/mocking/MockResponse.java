package com.easypost.mocking;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
public class MockResponse {
    private int statusCode;
    @Nullable
    private String content;

    /**
     * Construct a new MockResponse.
     *
     * @param statusCode The status code of the response.
     * @param content    The content of the response.
     */
    public MockResponse(int statusCode, @Nullable String content) {
        this.statusCode = statusCode;
        this.content = content;
    }

    /**
     * Construct a new MockResponse.
     *
     * @param statusCode The status code of the response.
     * @param data       The data to be serialized as JSON to be used as the content of the response.
     */
    public MockResponse(int statusCode, Object data) {
        this.statusCode = statusCode;
        this.content = new Gson().toJson(data);
    }
}
