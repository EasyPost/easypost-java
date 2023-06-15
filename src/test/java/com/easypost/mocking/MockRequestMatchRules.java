package com.easypost.mocking;

import com.easypost.http.Requestor.RequestMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockRequestMatchRules {
    private RequestMethod method;
    private String regex;

    /**
     * Construct a new MockRequestMatchRules.
     *
     * @param method The request HTTP method to match against.
     * @param regex  A regular expression to match against the request endpoint.
     */
    public MockRequestMatchRules(RequestMethod method, String regex) {
        this.method = method;
        this.regex = regex;
    }
}
