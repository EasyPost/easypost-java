package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum Mode implements EasyPostEnum {
    // https://stackoverflow.com/a/18851314
    @SerializedName ("test")
    TEST("test"),
    @SerializedName ("production")
    PRODUCTION("production");

    private String value;

    Mode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Mode getEnum(String value) throws EasyPostException {
        
        return (Mode) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
