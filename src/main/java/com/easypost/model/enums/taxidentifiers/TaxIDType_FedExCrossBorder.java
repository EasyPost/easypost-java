package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.easypost.model.enums.Mode;

public enum TaxIDType_FedExCrossBorder implements EasyPostEnum {
    VAT("VAT"),
    EORI("EORI"),
    IOSS("IOSS"),
    TIN("TIN");

    private String value;

    TaxIDType_FedExCrossBorder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_FedExCrossBorder getEnum(String value) throws EasyPostException {
        return (TaxIDType_FedExCrossBorder) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
