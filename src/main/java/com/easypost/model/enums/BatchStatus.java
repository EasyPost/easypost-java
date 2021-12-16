package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum BatchStatus implements EasyPostEnum {
    POSTAGE_PURCHASED("postage_purchased"),
    POSTAGE_PURCHASE_FAILED("postage_purchase_failed"),
    QUEUED_FOR_PURCHASE("queued_for_purchase"),
    CREATION_FAILED("creation_failed");

    private String value;

    BatchStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BatchStatus getEnum(String value) throws EasyPostException {
        return (BatchStatus) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
