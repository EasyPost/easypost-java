package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.google.gson.annotations.SerializedName;

public enum TaxIDType_DiaPost implements EasyPostEnum {
    @SerializedName ("VAT")
    VAT("VAT"),
    @SerializedName("IOSS")
    IOSS("IOSS");

    private String value;

    TaxIDType_DiaPost(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_DiaPost getEnum(String value) throws EasyPostException {
        return (TaxIDType_DiaPost) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
