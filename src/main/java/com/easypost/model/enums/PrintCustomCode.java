package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum PrintCustomCode implements EasyPostEnum {
    PO("PO"),
    DP("DP"),
    RMA("RMA"),
    AJ("AJ"),
    AT("AT"),
    BM("BM"),
    NINEV("9V"),
    ON("ON"),
    THREEQ("3Q"),
    IK("IK"),
    MK("MK"),
    MJ("MJ"),
    PM("PM"),
    PC("PC"),
    RQ("RQ"),
    RZ("RZ"),
    SA("SA"),
    SE("SE"),
    ST("ST"),
    TN("TN"),
    EI("EI"),
    TJ("TJ");

    private String value;

    PrintCustomCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PrintCustomCode getEnum(String value) throws EasyPostException {
        return (PrintCustomCode) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }

    public static PrintCustomCode getFedexDefault() {
        return null;
    }
}
