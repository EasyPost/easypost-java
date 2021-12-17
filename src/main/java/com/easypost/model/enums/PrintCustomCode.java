package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum PrintCustomCode implements EasyPostEnum {
    @SerializedName ("PO")
    PO("PO"),
    @SerializedName("DP")
    DP("DP"),
    @SerializedName("RMA")
    RMA("RMA"),
    @SerializedName("AJ")
    AJ("AJ"),
    @SerializedName("AT")
    AT("AT"),
    @SerializedName("DM")
    BM("BM"),
    @SerializedName("9V")
    NINEV("9V"),
    @SerializedName("ON")
    ON("ON"),
    @SerializedName("3Q")
    THREEQ("3Q"),
    @SerializedName("IK")
    IK("IK"),
    @SerializedName("MK")
    MK("MK"),
    @SerializedName("MJ")
    MJ("MJ"),
    @SerializedName("PM")
    PM("PM"),
    @SerializedName("PC")
    PC("PC"),
    @SerializedName("RQ")
    RQ("RQ"),
    @SerializedName("RZ")
    RZ("RZ"),
    @SerializedName("SA")
    SA("SA"),
    @SerializedName("SE")
    SE("SE"),
    @SerializedName("ST")
    ST("ST"),
    @SerializedName("TN")
    TN("TN"),
    @SerializedName("EI")
    EI("EI"),
    @SerializedName("TJ")
    TJ("TJ");

    private String value;

    PrintCustomCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PrintCustomCode getEnum(String value) throws EasyPostException {
        return (PrintCustomCode) EasyPostEnum.getEnumFromValue(values(), value);
    }

    public static PrintCustomCode getFedexDefault() {
        return null;
    }
}
