package com.easypost.mocking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockRequest {
    public MockRequestMatchRules matchRules;
    public MockResponse response;

    public MockRequest(MockRequestMatchRules matchRules, MockResponse response) {
        this.matchRules = matchRules;
        this.response = response;
    }
}
