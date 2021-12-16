package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum CustomsContentsType implements EasyPostEnum {
    DOCUMENTS("documents"),
    GIFT("gift"),
    MERCHANDISE("merchandise"),
    RETURNED_GOODS("returned_goods"),
    SAMPLE("sample"),
    OTHER("other");

    private String value;

    CustomsContentsType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CustomsContentsType getEnum(String value) throws EasyPostException {
        return (CustomsContentsType) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
