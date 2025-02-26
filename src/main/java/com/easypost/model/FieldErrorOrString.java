package com.easypost.model;

import lombok.Getter;

@Getter
public final class FieldErrorOrString {
    private final FieldError fieldError;
    private final String stringError;

    public FieldErrorOrString(FieldError fieldError) {
        this.fieldError = fieldError;
        this.stringError = null;
    }

    public FieldErrorOrString(String stringError) {
        this.fieldError = null;
        this.stringError = stringError;
    }

    public String getMessage() {
        if (fieldError != null) {
            return fieldError.getMessage();
        } else {
            return stringError;
        }
    }

    public String getField() {
        if (fieldError != null) {
            return fieldError.getField();
        } else {
            throw new UnsupportedOperationException("Field is not available for string error");
        }
    }
}
