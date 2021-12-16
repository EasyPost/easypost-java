package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum ReportStatus implements EasyPostEnum {
    NEW("new"),
    AVAILABLE("available"),
    FAILED("failed"),
    NULL(null);

    private String value;

    ReportStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ReportStatus getEnum(String value) throws EasyPostException {
        if (value == null) {
            return NULL;
        }
        return (ReportStatus) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
