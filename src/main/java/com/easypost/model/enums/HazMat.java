package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum HazMat implements EasyPostEnum {
    @SerializedName ("PRIMARY_CONTAINED")
    PRIMARY_CONTAINED("PRIMARY_CONTAINED"),
    @SerializedName("PRIMARY_PACKED")
    PRIMARY_PACKED("PRIMARY_PACKED"),
    @SerializedName("PRIMARY")
    PRIMARY("PRIMARY"),
    @SerializedName("SECONDARY_CONTAINED")
    SECONDARY_CONTAINED("SECONDARY_CONTAINED"),
    @SerializedName("SECONDARY_PACKED")
    SECONDARY_PACKED("SECONDARY_PACKED"),
    @SerializedName("SECONDARY")
    SECONDARY("SECONDARY"),
    @SerializedName("ORMD")
    ORMD("ORMD"),
    @SerializedName("LIMITED_QUANTITY")
    LIMITED_QUANTITY("LIMITED_QUANTITY"),
    @SerializedName("LITHIUM")
    LITHIUM("LITHIUM");

    private String value;

    HazMat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HazMat getEnum(String value) throws EasyPostException {
        return (HazMat) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
