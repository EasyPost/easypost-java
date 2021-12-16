package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum PickupStatus implements EasyPostEnum {
    @SerializedName("unknown")
    UNKNOWN("unknown"),
    @SerializedName("scheduled")
    SCHEDULED("scheduled"),
    @SerializedName("canceled")
    CANCELLED("canceled"); // handles single to double L confusion, the catalyst for using enums

    private String value;

    PickupStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PickupStatus getEnum(String value) throws EasyPostException {
        return (PickupStatus) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
