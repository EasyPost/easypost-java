package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.google.gson.annotations.SerializedName;

public enum TaxIDType_UPS implements EasyPostEnum {
    @SerializedName ("VAT")
    VAT("VAT"),
    @SerializedName("IOSS")
    IOSS("IOSS"),
    @SerializedName("VOEC")
    VOEC("VOEC"),
    @SerializedName("HMRC")
    HMRC("HMRC");

    private String value;

    TaxIDType_UPS(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_UPS getEnum(String value) throws EasyPostException {
        return (TaxIDType_UPS) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
