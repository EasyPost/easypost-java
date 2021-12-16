package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum FeeType implements EasyPostEnum {
    LABEL("LabelFee"),
    POSTAGE("PostageFee"),
    INSURANCE("InsuranceFee"),
    TRACKER("TrackerFee");

    private String value;

    FeeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FeeType getEnum(String value) throws EasyPostException {
        return (FeeType) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
