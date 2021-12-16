package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum ScanFormStatus implements EasyPostEnum {
    CREATING("creating"),
    CREATED("created"),
    FAILED("failed");

    private String value;

    ScanFormStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ScanFormStatus getEnum(String value) throws EasyPostException {
        return (ScanFormStatus) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
