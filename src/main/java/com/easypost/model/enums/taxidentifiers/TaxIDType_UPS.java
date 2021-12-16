package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;

public enum TaxIDType_UPS implements EasyPostEnum {
    VAT("VAT"),
    IOSS("IOSS"),
    VOEC("VOEC"),
    HMRC("HMRC");

    private String value;

    TaxIDType_UPS(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_UPS getEnum(String value) throws EasyPostException {
        return (TaxIDType_UPS) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
