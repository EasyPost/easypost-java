package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.google.gson.annotations.SerializedName;

public enum TaxIDType_FedExCrossBorder implements EasyPostEnum {
    @SerializedName ("VAT")
    VAT("VAT"),
    @SerializedName("EORI")
    EORI("EORI"),
    @SerializedName("IOSS")
    IOSS("IOSS"),
    @SerializedName("TIN")
    TIN("TIN");

    private String value;

    TaxIDType_FedExCrossBorder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_FedExCrossBorder getEnum(String value) throws EasyPostException {
        return (TaxIDType_FedExCrossBorder) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
