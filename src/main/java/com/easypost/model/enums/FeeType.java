package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum FeeType implements EasyPostEnum {
    @SerializedName("LabelFee")
    LABEL("LabelFee"),
    @SerializedName("PostageFee")
    POSTAGE("PostageFee"),
    @SerializedName("InsuranceFee")
    INSURANCE("InsuranceFee"),
    @SerializedName("TrackerFee")
    TRACKER("TrackerFee");

    private String value;

    FeeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FeeType getEnum(String value) throws EasyPostException {
        return (FeeType) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
