package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum Customs_EEL_PFC implements EasyPostEnum {
    EEL("EEL"),
    PFC("PFC");

    private String value;

    Customs_EEL_PFC(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Customs_EEL_PFC getEnum(String value)
            throws EasyPostException {
        return (Customs_EEL_PFC) EasyPostEnum.getEnumFromValue(values(),
                value);
    }
}
