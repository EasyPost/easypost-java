package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum RefundStatus implements EasyPostEnum {
    @SerializedName("submitted")
    SUBMITTED("submitted"),
    @SerializedName("refunded")
    REFUNDED("refunded"),
    @SerializedName("rejected")
    REJECTED("rejected");

    private String value;

    RefundStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RefundStatus getEnum(String value) throws EasyPostException {
        return (RefundStatus) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
