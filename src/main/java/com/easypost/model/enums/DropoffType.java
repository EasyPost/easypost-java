package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum DropoffType implements EasyPostEnum {
    REGULAR_PICKUP("REGULAR_PICKUP"),
    SCHEDULED_PICKUP("SCHEDULED_PICKUP"),
    RETAIL_LOCATION("rejected"),
    STATION("STATION"),
    DROP_BOX("DROP_BOX"),
    FEDEX_REGULAR_PICKUP("REGULAR_PICKUP"),
    FEDEX_SCHEDULED_PICKUP("REQUEST_COURIER"),
    FEDEX_RETAIL_LOCATION("BUSINESS_SERVICE_CENTER"),
    FEDEX_STATION("STATION"),
    FEDEX_DROP_BOX("DROP_BOX");

    private String value;

    DropoffType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DropoffType getEnum(String value) throws EasyPostException {
        return (DropoffType) EasyPostEnum.getEnumFromValue(Mode.class, value);
    }
}
