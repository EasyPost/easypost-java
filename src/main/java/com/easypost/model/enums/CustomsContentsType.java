package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum CustomsContentsType implements EasyPostEnum {
    @SerializedName("documents")
    DOCUMENTS("documents"),
    @SerializedName("gift")
    GIFT("gift"),
    @SerializedName("merchandise")
    MERCHANDISE("merchandise"),
    @SerializedName("returned_goods")
    RETURNED_GOODS("returned_goods"),
    @SerializedName("sample")
    SAMPLE("sample"),
    @SerializedName("other")
    OTHER("other");

    private String value;

    CustomsContentsType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CustomsContentsType getEnum(String value) throws EasyPostException {
        return (CustomsContentsType) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
