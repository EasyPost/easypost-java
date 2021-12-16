package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum CustomsRestrictionType implements EasyPostEnum {
    @SerializedName("none")
    NONE("none"),
    @SerializedName("other")
    OTHER("other"),
    @SerializedName("quarantine")
    QUARANTINE("quarantine"),
    @SerializedName("sanitary_phytosanitary_inspection")
    SANITARY_PHYTOSANITARY_INSPECTION("sanitary_phytosanitary_inspection");

    private String value;

    CustomsRestrictionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CustomsRestrictionType getEnum(String value)
            throws EasyPostException {
        return (CustomsRestrictionType) EasyPostEnum.getEnumFromValue(
                values(), value);
    }
}
