package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum CustomsNonDeliveryOption implements EasyPostEnum {
    @SerializedName("abandon")
    ABANDON("abandon"),
    @SerializedName("return")
    RETURN("return");

    private String value;

    CustomsNonDeliveryOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CustomsNonDeliveryOption getEnum(String value)
            throws EasyPostException {
        return (CustomsNonDeliveryOption) EasyPostEnum.getEnumFromValue(
                values(), value);
    }

    public static CustomsNonDeliveryOption getDefault() {
        return RETURN;
    }
}
