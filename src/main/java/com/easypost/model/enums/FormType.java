package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum FormType implements EasyPostEnum {
    @SerializedName("high_value_report")
    HIGH_VALUE_REPORT("high_value_report"),
    @SerializedName("commercial_invoice")
    COMMERCIAL_INVOICE("commercial_invoice"),
    @SerializedName("cod_return_label")
    COD_RETURN_LABEL("cod_return_label"),
    @SerializedName("order_summary")
    ORDER_SUMMARY("order_summary"),
    @SerializedName("cn22")
    CN22("cn22");

    private String value;

    FormType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FormType getEnum(String value) throws EasyPostException {
        return (FormType) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
