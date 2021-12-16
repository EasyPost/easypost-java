package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum TrackerStatus implements EasyPostEnum {
    @SerializedName("unknown")
    UNKNOWN("unknown"),
    @SerializedName("pre_transit")
    PRE_TRANSIT("pre_transit"),
    @SerializedName("in_transit")
    IN_TRANSIT("in_transit"),
    @SerializedName("out_for_delivery")
    OUT_FOR_DELIVERY("out_for_delivery"),
    @SerializedName("delivered")
    DELIVERED("delivered"),
    @SerializedName("available_for_pickup")
    AVAILABLE_FOR_PICKUP("available_for_pickup"),
    @SerializedName("return_to_sender")
    RETURN_TO_SENDER("return_to_sender"),
    @SerializedName("failure")
    FAILURE("failure"),
    @SerializedName("cancelled")
    CANCELLED("cancelled"),
    @SerializedName("error")
    ERROR("error");

    private String value;

    TrackerStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TrackerStatus getEnum(String value) throws EasyPostException {
        return (TrackerStatus) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
