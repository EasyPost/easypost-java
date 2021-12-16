package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum RefundStatus implements EasyPostEnum {
    SUBMITTED("submitted"),
    REFUNDED("refunded"),
    REJECTED("rejected");

    private String value;

    RefundStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RefundStatus getEnum(String value) throws EasyPostException {
        return (RefundStatus) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
