package com.easypost.mocking;
import com.easypost.http.Requestor.RequestMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockRequestMatchRules {
    public RequestMethod method;
    public String regex;

    public MockRequestMatchRules(RequestMethod method, String regex) {
        this.method = method;
        this.regex = regex;
    }
}
