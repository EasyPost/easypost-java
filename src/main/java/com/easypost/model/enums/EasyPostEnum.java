package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public interface EasyPostEnum {

    public String getValue();

    public static EasyPostEnum getEnumFromValue(EasyPostEnum[] values, String value) throws EasyPostException {
        for (EasyPostEnum v : values) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        throw new EasyPostException("Invalid enum: " + value);
    }
}
