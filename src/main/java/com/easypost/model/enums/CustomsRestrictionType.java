package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum CustomsRestrictionType implements EasyPostEnum {
    NONE("none"), OTHER("other"), QUARANTINE("quarantine"),
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
                Mode.class, value);
    }
}
