package com.easypost.model;

import lombok.Getter;

@Getter
public class AddressVerifyResponse {
    private Address address;
    private String message;
}
