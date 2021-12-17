package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum USPSSpecialRatesEligibility implements EasyPostEnum {
    @SerializedName ("USPS.MEDIAMAIL")
    MEDIA_MAIL("USPS.MEDIAMAIL"),
    @SerializedName("USPS.LIBRARYMAIL")
    LIBRARY_MAIL("USPS.LIBRARYMAIL");

    private String value;

    USPSSpecialRatesEligibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static USPSSpecialRatesEligibility getEnum(String value) throws EasyPostException {
        return (USPSSpecialRatesEligibility) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
