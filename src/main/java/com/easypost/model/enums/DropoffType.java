package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum DropoffType implements EasyPostEnum {
    @SerializedName ("REGULAR_PICKUP")
    REGULAR_PICKUP("REGULAR_PICKUP"),
    @SerializedName("SCHEDULED_PICKUP")
    SCHEDULED_PICKUP("SCHEDULED_PICKUP"),
    @SerializedName("RETAIL_LOCATION")
    RETAIL_LOCATION("RETAIL_LOCATION"),
    @SerializedName("STATION")
    STATION("STATION"),
    @SerializedName("DROP_BOX")
    DROP_BOX("DROP_BOX"),
    @SerializedName("REQUEST_COURIER")
    FEDEX_SCHEDULED_PICKUP("REQUEST_COURIER"),
    @SerializedName("BUSINESS_SERVICE_CENTER")
    FEDEX_RETAIL_LOCATION("BUSINESS_SERVICE_CENTER");

    private String value;

    DropoffType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DropoffType getEnum(String value) throws EasyPostException {
        return (DropoffType) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
