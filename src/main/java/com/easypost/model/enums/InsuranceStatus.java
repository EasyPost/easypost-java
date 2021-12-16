package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum InsuranceStatus implements EasyPostEnum {
    @SerializedName("new")
    NEW("new"),
    @SerializedName("pending")
    PENDING("pending"),
    @SerializedName("purchased")
    PURCHASED("purchased"),
    @SerializedName("failed")
    FAILED("failed"),
    @SerializedName("cancelled")
    CANCELLED("cancelled");

    private String value;

    InsuranceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static InsuranceStatus getEnum(String value) throws EasyPostException {
        return (InsuranceStatus) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
