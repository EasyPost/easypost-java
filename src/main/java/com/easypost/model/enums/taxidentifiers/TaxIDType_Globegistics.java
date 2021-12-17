package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.google.gson.annotations.SerializedName;

public enum TaxIDType_Globegistics implements EasyPostEnum {
    @SerializedName ("VAT")
    VAT("VAT");

    private String value;

    TaxIDType_Globegistics(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_Globegistics getEnum(String value) throws EasyPostException {
        return (TaxIDType_Globegistics) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
