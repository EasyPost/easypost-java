package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum TaxEntity implements EasyPostEnum {
    SENDER("SENDER"),
    RECEIVER("RECEIVER");

    private String value;

    TaxEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxEntity getEnum(String value) throws EasyPostException {
        return (TaxEntity) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
