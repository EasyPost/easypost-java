package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum CustomsNonDeliveryOption implements EasyPostEnum {
    ABANDON("abandon"),
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
                Mode.class, value);
    }

    public static CustomsNonDeliveryOption getDefault() {
        return RETURN;
    }
}
