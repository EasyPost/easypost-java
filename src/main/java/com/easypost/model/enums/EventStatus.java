package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum EventStatus implements EasyPostEnum {
    @SerializedName ("completed")
    COMPLETED("completed"),
    @SerializedName("failed")
    FAILED("failed"),
    @SerializedName("in_queue")
    IN_QUEUE("in_queue"),
    @SerializedName("retrying")
    RETRYING("retrying"),
    @Deprecated
    @SerializedName("pending")
    PENDING("pending");

    private String value;

    EventStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EventStatus getEnum(String value) throws EasyPostException {
        return (EventStatus) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
