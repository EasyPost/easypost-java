package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum BatchStatus implements EasyPostEnum {
    @SerializedName("postage_purchased")
    POSTAGE_PURCHASED("postage_purchased"),
    @SerializedName("postage_purchase_failed")
    POSTAGE_PURCHASE_FAILED("postage_purchase_failed"),
    @SerializedName("queued_for_purchase")
    QUEUED_FOR_PURCHASE("queued_for_purchase"),
    @SerializedName("creation_failed")
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
