package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum Incoterm implements EasyPostEnum {
    @SerializedName ("EXW")
    EXW("EXW"),
    @SerializedName("FCA")
    FCA("FCA"),
    @SerializedName("CPT")
    CPT("CPT"),
    @SerializedName("CIP")
    CIP("CIP"),
    @SerializedName("DAT")
    DAT("DAT"),
    @SerializedName("DAP")
    DAP("DAP"),
    @SerializedName("DDP")
    DDP("DDP"),
    @SerializedName("FAS")
    FAS("FAS"),
    @SerializedName("FOB")
    FOB("FOB"),
    @SerializedName("CFR")
    CFR("CFR"),
    @SerializedName("CIF")
    CIF("CIF");

    private String value;

    Incoterm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Incoterm getEnum(String value) throws EasyPostException {
        return (Incoterm) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
