package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum Payment implements EasyPostEnum {
    SENDER("SENDER"),
    THIRD_PARTY("THIRD_PARTY"),
    RECEIVER("RECEIVER"),
    COLLECT("COLLECT");

    private String value;

    Payment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Payment getEnum(String value) throws EasyPostException {
        return (Payment) EasyPostEnum.getEnumFromValue(values(), value);
    }

    public static Payment getDefault() {
        return SENDER;
    }
}
