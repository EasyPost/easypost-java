package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.easypost.model.enums.Mode;

public enum TaxIDType_DPDUK implements EasyPostEnum {
    VAT("VAT"),
    EORI("EORI"),
    PID("PID"),
    IOSS("IOSS");

    private String value;

    TaxIDType_DPDUK(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_DPDUK getEnum(String value) throws EasyPostException {
        return (TaxIDType_DPDUK) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
