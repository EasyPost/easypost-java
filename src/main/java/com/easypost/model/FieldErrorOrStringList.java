package com.easypost.model;

import lombok.Getter;

import java.util.AbstractList;
import java.util.List;

@Getter
public class FieldErrorOrStringList extends AbstractList<Object> {
    private final List<FieldError> errorList;
    private final List<String> stringList;

    private FieldErrorOrStringList(List<FieldError> errorList, List<String> stringList) {
        this.errorList = errorList;
        this.stringList = stringList;
    }

    public static FieldErrorOrStringList fromErrorList(List<FieldError> errorList) {
        return new FieldErrorOrStringList(errorList, null);
    }

    public static FieldErrorOrStringList fromStringList(List<String> stringList) {
        return new FieldErrorOrStringList(null, stringList);
    }

    public boolean isErrorList() {
        return errorList != null;
    }

    public boolean isStringList() {
        return stringList != null;
    }
    
    @Override
    public Object get(int index) {
        if (isErrorList()) {
            return errorList.get(index);
        } else if (isStringList()) {
            return stringList.get(index);
        }
        throw new IndexOutOfBoundsException("Index out of bounds or list is empty");
    }

    @Override
    public int size() {
        if (isErrorList()) {
            return errorList.size();
        } else if (isStringList()) {
            return stringList.size();
        }
        return 0;
    }

    public String getMessage(int index) {
        if (isErrorList()) {
            return errorList.get(index).getMessage();
        } else if (isStringList()) {
            return stringList.get(index);
        }
        throw new IndexOutOfBoundsException("Index out of bounds or list is empty");
    }

    public String getField(int index) {
        if (isErrorList()) {
            return errorList.get(index).getField();
        }
        throw new UnsupportedOperationException("Field is not available for string list");
    }
}
