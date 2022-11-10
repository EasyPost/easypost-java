package com.easypost.model;

import lombok.Getter;

@Getter
public class AddressVerifications extends EasyPostResource {
    private AddressVerification zip4;
    private AddressVerification delivery;
}
