package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public interface EasyPostEnum {

    public String getValue();

    public static Enum getEnumFromValue(Class enumClazz, String value) throws EasyPostException {
        try {
            return Enum.valueOf(enumClazz, value);
        } catch (IllegalArgumentException e) {
            throw new EasyPostException("Invalid enum: " + value);
        }
    }
}
