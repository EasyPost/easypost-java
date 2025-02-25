package com.easypost.model;

import lombok.Getter;

import java.util.List;

@Getter
@SuppressWarnings("JavaLangClash")
public final class Error {
    private String message;
    private String code;
    private FieldErrorOrStringList errors;

    /**
     * Set the message of this error object.
     *
     * @param message The error message.
     */
    void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Set the code of this error object.
     *
     * @param code The error code.
     */
    void setCode(final String code) {
        this.code = code;
    }

    /**
     * Set the errors of this error object.
     *
     * @param errors The errors.
     */
    void setErrors(final Object errors) {
        if (errors instanceof List) {
            List<?> errorList = (List<?>) errors;
            if (!errorList.isEmpty()) {
                if (errorList.get(0) instanceof FieldError) {
                    this.errors = FieldErrorOrStringList.fromErrorList((List<FieldError>) errorList);
                } else if (errorList.get(0) instanceof String) {
                    this.errors = FieldErrorOrStringList.fromStringList((List<String>) errorList);
                }
            }
        } else if (errors instanceof FieldErrorOrStringList) {
            this.errors = (FieldErrorOrStringList) errors;
        } else {
            throw new IllegalArgumentException("Invalid type for errors");
        }
    }
}
