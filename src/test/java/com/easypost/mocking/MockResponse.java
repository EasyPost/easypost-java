package com.easypost.mocking;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
public class MockResponse {
    public int statusCode;
    @Nullable public String content;

    public MockResponse(int statusCode, @Nullable String content) {
        this.statusCode = statusCode;
        this.content = content;
    }

    public MockResponse(int statusCode, Object data) {
        this.statusCode = statusCode;
        this.content = new Gson().toJson(data);
    }
}
