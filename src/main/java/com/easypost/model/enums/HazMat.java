package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum HazMat implements EasyPostEnum {
    PRIMARY_CONTAINED("PRIMARY_CONTAINED"),
    PRIMARY_PACKED("PRIMARY_PACKED"),
    PRIMARY("PRIMARY"),
    SECONDARY_CONTAINED("SECONDARY_CONTAINED"),
    SECONDARY_PACKED("SECONDARY_PACKED"),
    SECONDARY("SECONDARY"),
    ORMD("ORMD"),
    LIMITED_QUANTITY("LIMITED_QUANTITY"),
    LITHIUM("LITHIUM");

    private String value;

    HazMat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HazMat getEnum(String value) throws EasyPostException {
        return (HazMat) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
