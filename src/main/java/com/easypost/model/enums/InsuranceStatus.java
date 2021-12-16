package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum InsuranceStatus implements EasyPostEnum {
    NEW("new"),
    PENDING("pending"),
    PURCHASED("purchased"),
    FAILED("failed"),
    CANCELLED("cancelled");

    private String value;

    InsuranceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static InsuranceStatus getEnum(String value) throws EasyPostException {
        return (InsuranceStatus) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
