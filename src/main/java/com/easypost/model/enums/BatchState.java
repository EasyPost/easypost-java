package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum BatchState implements EasyPostEnum {
    @SerializedName("creating")
    CREATING("creating"),
    @SerializedName("creation_failed")
    CREATION_FAILED("creation_failed"),
    @SerializedName("created")
    CREATED("created"),
    @SerializedName("purchasing")
    PURCHASING("purchasing"),
    @SerializedName("purchase_failed")
    PURCHASE_FAILED("purchase_failed"),
    @SerializedName("purchased")
    PURCHASED("purchased"),
    @SerializedName("label_generating")
    LABEL_GENERATING("label_generating"),
    @SerializedName("label_generated")
    LABEL_GENERATED("label_generated");

    private String value;

    BatchState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BatchState getEnum(String value) throws EasyPostException {
        return (BatchState) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
