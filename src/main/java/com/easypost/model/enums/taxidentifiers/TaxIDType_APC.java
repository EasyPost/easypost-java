package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;

public enum TaxIDType_APC implements EasyPostEnum {
    VAT("VAT");

    private String value;

    TaxIDType_APC(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_APC getEnum(String value) throws EasyPostException {
        return (TaxIDType_APC) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
