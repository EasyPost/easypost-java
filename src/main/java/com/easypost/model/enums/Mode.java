package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum Mode implements EasyPostEnum {
    TEST("test"),
    PRODUCTION("production");

    private String value;

    Mode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Mode getEnum(String value) throws EasyPostException {
        return (Mode) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
