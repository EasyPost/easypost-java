package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;

public enum TaxIDType_DHLAsia implements EasyPostEnum {
    VAT("VAT"),
    GST("GST"),
    EORI("EORI"),
    IOSS("IOSS"),
    PAN("PAN");

    private String value;

    TaxIDType_DHLAsia(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_DHLAsia getEnum(String value) throws EasyPostException {
        return (TaxIDType_DHLAsia) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
