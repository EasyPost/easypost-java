package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum Incoterm implements EasyPostEnum {
    EXW("EXW"),
    FCA("FCA"),
    CPT("CPT"),
    CIP("CIP"),
    DAT("DAT"),
    DAP("DAP"),
    DDP("DDP"),
    FAS("FAS"),
    FOB("FOB"),
    CFR("CFR"),
    CIF("CIF");

    private String value;

    Incoterm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Incoterm getEnum(String value) throws EasyPostException {
        return (Incoterm) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
