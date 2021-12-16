package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.easypost.model.enums.Mode;

public enum TaxIDType_AsendiaUSA implements EasyPostEnum {
    VAT("VAT");

    private String value;

    TaxIDType_AsendiaUSA(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_AsendiaUSA getEnum(String value) throws EasyPostException {
        return (TaxIDType_AsendiaUSA) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
