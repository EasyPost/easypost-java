package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum ScanFormStatus implements EasyPostEnum {
    @SerializedName("creating")
    CREATING("creating"),
    @SerializedName("created")
    CREATED("created"),
    @SerializedName("failed")
    FAILED("failed");

    private String value;

    ScanFormStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ScanFormStatus getEnum(String value) throws EasyPostException {
        return (ScanFormStatus) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
