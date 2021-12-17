package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;
import com.google.gson.annotations.SerializedName;

public enum TaxIDType_FedExMailview implements EasyPostEnum {
    @SerializedName ("TIN")
    TIN("TIN");

    private String value;

    TaxIDType_FedExMailview(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_FedExMailview getEnum(String value) throws EasyPostException {
        return (TaxIDType_FedExMailview) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
