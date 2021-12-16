package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum TrackerStatus implements EasyPostEnum {
    UNKNOWN("unknown"),
    PRE_TRANSIT("pre_transit"),
    IN_TRANSIT("in_transit"),
    OUT_FOR_DELIVERY("out_for_delivery"),
    DELIVERED("delivered"),
    AVAILABLE_FOR_PICKUP("available_for_pickup"),
    RETURN_TO_SENDER("return_to_sender"),
    FAILURE("failure"),
    CANCELLED("cancelled"),
    ERROR("error");

    private String value;

    TrackerStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TrackerStatus getEnum(String value) throws EasyPostException {
        return (TrackerStatus) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
