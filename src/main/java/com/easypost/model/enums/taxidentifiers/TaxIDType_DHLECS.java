package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.google.gson.annotations.SerializedName;

public enum TaxIDType_DHLECS implements EasyPostEnum {
    @SerializedName ("VAT")
    VAT("VAT"),
    @SerializedName("GST")
    GST("GST"),
    @SerializedName("EORI")
    EORI("EORI"),
    @SerializedName("IOSS")
    IOSS("IOSS"),
    @SerializedName("PAN")
    PAN("PAN"),
    @SerializedName("OTHER")
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
