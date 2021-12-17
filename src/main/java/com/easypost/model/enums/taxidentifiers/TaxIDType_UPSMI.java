package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.google.gson.annotations.SerializedName;

public enum TaxIDType_UPSMI implements EasyPostEnum {
    @SerializedName ("VAT")
    VAT("VAT");

    private String value;

    TaxIDType_UPSMI(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_UPSMI getEnum(String value) throws EasyPostException {
        return (TaxIDType_UPSMI) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
