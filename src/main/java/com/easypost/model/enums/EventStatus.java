package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum EventStatus implements EasyPostEnum {
    COMPLETED("completed"),
    FAILED("failed"),
    IN_QUEUE("in_queue"),
    RETRYING("retrying"),
    @Deprecated
    PENDING("pending");

    private String value;

    EventStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EventStatus getEnum(String value) throws EasyPostException {
        return (EventStatus) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
