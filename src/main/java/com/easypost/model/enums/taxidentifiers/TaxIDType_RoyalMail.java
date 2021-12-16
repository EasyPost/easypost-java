package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.easypost.model.enums.Mode;

public enum TaxIDType_RoyalMail implements EasyPostEnum {
    VAT("VAT"),
    EORI("EORI"),
    IOSS("IOSS");

    private String value;

    TaxIDType_RoyalMail(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_RoyalMail getEnum(String value) throws EasyPostException {
        return (TaxIDType_RoyalMail) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
