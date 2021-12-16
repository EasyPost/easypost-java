package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum CredentialsVisibility implements EasyPostEnum {
    VISIBLE("visible"),
    CHECKBOX("checkbox"),
    FAKE("fake"),
    PASSWORD("password"),
    MASKED("masked");

    private String value;

    CredentialsVisibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CredentialsVisibility getEnum(String value) throws EasyPostException {
        return (CredentialsVisibility) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
