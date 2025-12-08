package com.easypost.model;

import java.util.Map;

import lombok.Getter;

@Getter
public final class FedExAccountValidationResponse extends EasyPostResource {
    private String emailAddress;
    private Map<String, String> options;
    private String phoneNumber;
}
