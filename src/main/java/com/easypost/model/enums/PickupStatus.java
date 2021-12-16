package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum PickupStatus implements EasyPostEnum {
    UNKNOWN("unknown"),
    SCHEDULED("scheduled"),
    CANCELLED("canceled"); // handles single to double L confusion, the catalyst for using enums

    private String value;

    PickupStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PickupStatus getEnum(String value) throws EasyPostException {
        return (PickupStatus) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
