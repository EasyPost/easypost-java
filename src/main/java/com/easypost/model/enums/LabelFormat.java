package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum LabelFormat implements EasyPostEnum {
    @SerializedName ("PNG")
    PNG("PNG"),
    @SerializedName("PDF")
    PDF("PDF"),
    @SerializedName("ZPL")
    ZPL("ZPL"),
    @SerializedName("EPL2")
    EPL2("EPL2");

    private String value;

    LabelFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LabelFormat getEnum(String value) throws EasyPostException {
        return (LabelFormat) EasyPostEnum.getEnumFromValue(values(), value);
    }

    public static LabelFormat getDefault() {
        return PNG;
    }
}
