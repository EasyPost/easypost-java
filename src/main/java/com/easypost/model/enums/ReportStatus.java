package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum ReportStatus implements EasyPostEnum {
    @SerializedName("new")
    NEW("new"),
    @SerializedName("available")
    AVAILABLE("available"),
    @SerializedName("failed")
    FAILED("failed"),
    NULL(null);

    private String value;

    ReportStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ReportStatus getEnum(String value) throws EasyPostException {
        if (value == null) {
            return NULL;
        }
        return (ReportStatus) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
