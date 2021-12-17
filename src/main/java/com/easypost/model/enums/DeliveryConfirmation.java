package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum DeliveryConfirmation implements EasyPostEnum {
    @SerializedName ("ADULT_SIGNATURE")
    ADULT_SIGNATURE("ADULT_SIGNATURE"),
    @SerializedName("SIGNATURE")
    SIGNATURE("SIGNATURE"),
    @SerializedName("NO_SIGNATURE")
    NO_SIGNATURE("NO_SIGNATURE");

    private String value;

    DeliveryConfirmation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DeliveryConfirmation getEnum(String value) throws EasyPostException {
        return (DeliveryConfirmation) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
