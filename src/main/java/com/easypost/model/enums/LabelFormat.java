package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum LabelFormat implements EasyPostEnum {
    PNG("PNG"),
    PDF("PDF"),
    ZPL("ZPL"),
    EPL2("EPL2");

    private String value;

    LabelFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LabelFormat getEnum(String value) throws EasyPostException {
        return (LabelFormat) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }

    public static LabelFormat getDefault() {
        return PNG;
    }
}
