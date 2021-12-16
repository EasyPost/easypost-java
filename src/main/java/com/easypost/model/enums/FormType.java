package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum FormType implements EasyPostEnum {
    HIGH_VALUE_REPORT("high_value_report"),
    COMMERCIAL_INVOICE("commercial_invoice"),
    COD_RETURN_LABEL("cod_return_label"),
    ORDER_SUMMARY("order_summary"),
    CN22("cn22");

    private String value;

    FormType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FormType getEnum(String value) throws EasyPostException {
        return (FormType) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
