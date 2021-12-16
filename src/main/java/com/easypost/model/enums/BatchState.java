package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum BatchState implements EasyPostEnum {
    CREATING("creating"),
    CREATION_FAILED("creation_failed"),
    CREATED("created"),
    PURCHASING("purchasing"),
    PURCHASE_FAILED("purchase_failed"),
    PURCHASED("purchased"),
    LABEL_GENERATING("label_generating"),
    LABEL_GENERATED("label_generated");

    private String value;

    BatchState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BatchState getEnum(String value) throws EasyPostException {
        return (BatchState) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
