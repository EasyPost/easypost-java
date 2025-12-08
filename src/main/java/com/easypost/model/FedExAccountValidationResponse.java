package com.easypost.model;

import java.util.Map;
import lombok.Getter;

@Getter
public final class FedExAccountValidationResponse {
    // If the response contains the following, one must complete pin or invoice validation next
    private String emailAddress;
    private Map<String, String> options;
    private String phoneNumber;

    // If the response contains the following, pre-validation has been completed
    private String id;
    private String object;
    private String type;
    private Map<String, String> credentials;
}
