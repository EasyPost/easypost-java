package com.easypost.mocking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockRequest {
    private MockRequestMatchRules matchRules;
    private MockResponse response;

    /**
     * Construct a new MockRequest.
     *
     * @param matchRules The match rules for the request.
     * @param response   The response to be returned when the request matches.
     */
    public MockRequest(MockRequestMatchRules matchRules, MockResponse response) {
        this.matchRules = matchRules;
        this.response = response;
    }
}
