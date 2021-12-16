package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum USPSSpecialRatesEligibility implements EasyPostEnum {
    MEDIA_MAIL("USPS.MEDIAMAIL"),
    LIBRARY_MAIL("USPS.LIBRARYMAIL");

    private String value;

    USPSSpecialRatesEligibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static USPSSpecialRatesEligibility getEnum(String value) throws EasyPostException {
        return (USPSSpecialRatesEligibility) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
