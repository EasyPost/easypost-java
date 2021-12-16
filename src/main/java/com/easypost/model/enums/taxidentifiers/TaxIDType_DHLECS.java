package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;

public enum TaxIDType_DHLECS implements EasyPostEnum {
    VAT("VAT"),
    GST("GST"),
    EORI("EORI"),
    IOSS("IOSS"),
    PAN("PAN"),
    OTHER("OTHER");

    private String value;

    TaxIDType_DHLECS(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_DHLECS getEnum(String value) throws EasyPostException {
        return (TaxIDType_DHLECS) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
