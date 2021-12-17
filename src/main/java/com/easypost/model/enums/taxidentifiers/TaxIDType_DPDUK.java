package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.google.gson.annotations.SerializedName;

public enum TaxIDType_DPDUK implements EasyPostEnum {
    @SerializedName ("VAT")
    VAT("VAT"),
    @SerializedName("EORI")
    EORI("EORI"),
    @SerializedName("PID")
    PID("PID"),
    @SerializedName("IOSS")
    IOSS("IOSS");

    private String value;

    TaxIDType_DPDUK(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_DPDUK getEnum(String value) throws EasyPostException {
        return (TaxIDType_DPDUK) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
