package com.easypost.model;

import lombok.Getter;

@Getter
public final class Address extends BaseAddress {
    private String message;
    private String carrierFacility;
    private String federalTaxId;
    private Boolean residential;
    private AddressVerifications verifications;
}
