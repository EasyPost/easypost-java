package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;

public enum TaxIDType_DHLExpress implements EasyPostEnum {
    VAT("VAT"),
    SDT("SDT"),
    IOSS("IOSS"),
    FTZ("FTZ"),
    DAN("DAN"),
    TAN("TAN"),
    DTF("DTF"),
    CNP("CNP"),
    DUN("DUN"),
    EIN("EIN"),
    EOR("EOR"),
    SSN("SSN"),
    FED("FED"),
    STA("STA");

    private String value;

    TaxIDType_DHLExpress(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_DHLExpress getEnum(String value) throws EasyPostException {
        return (TaxIDType_DHLExpress) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
