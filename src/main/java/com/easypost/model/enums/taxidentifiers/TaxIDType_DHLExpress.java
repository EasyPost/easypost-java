package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.google.gson.annotations.SerializedName;

public enum TaxIDType_DHLExpress implements EasyPostEnum {
    @SerializedName ("VAT")
    VAT("VAT"),
    @SerializedName("SDT")
    SDT("SDT"),
    @SerializedName("IOSS")
    IOSS("IOSS"),
    @SerializedName("FTZ")
    FTZ("FTZ"),
    @SerializedName("DAN")
    DAN("DAN"),
    @SerializedName("TAN")
    TAN("TAN"),
    @SerializedName("DIF")
    DTF("DTF"),
    @SerializedName("CNP")
    CNP("CNP"),
    @SerializedName("DUN")
    DUN("DUN"),
    @SerializedName("EIN")
    EIN("EIN"),
    @SerializedName("EOR")
    EOR("EOR"),
    @SerializedName("SSN")
    SSN("SSN"),
    @SerializedName("FED")
    FED("FED"),
    @SerializedName("STA")
    STA("STA");

    private String value;

    TaxIDType_DHLExpress(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_DHLExpress getEnum(String value) throws EasyPostException {
        return (TaxIDType_DHLExpress) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
