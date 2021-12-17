package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum CredentialsVisibility implements EasyPostEnum {
    @SerializedName("visible")
    VISIBLE("visible"),
    @SerializedName("checkbox")
    CHECKBOX("checkbox"),
    @SerializedName("fake")
    FAKE("fake"),
    @SerializedName("password")
    PASSWORD("password"),
    @SerializedName("masked")
    MASKED("masked");

    private String value;

    CredentialsVisibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CredentialsVisibility getEnum(String value) throws EasyPostException {
        return (CredentialsVisibility) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
