package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum Payment implements EasyPostEnum {
    @SerializedName ("SENDER")
    SENDER("SENDER"),
    @SerializedName("THIRD_PARTY")
    THIRD_PARTY("THIRD_PARTY"),
    @SerializedName("RECEIVER")
    RECEIVER("RECEIVER"),
    @SerializedName("COLLECT")
    COLLECT("COLLECT");

    private String value;

    Payment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Payment getEnum(String value) throws EasyPostException {
        return (Payment) EasyPostEnum.getEnumFromValue(values(), value);
    }

    public static Payment getDefault() {
        return SENDER;
    }
}
