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
    void setErrors(final FieldErrorOrStringList errors) {
        this.errors = errors;
    }

    /**
     * Set the errors of this error object from a list of FieldError objects.
     *
     * @param errorList The list of FieldError objects.
     */
    void setErrors(final List<FieldError> errorList) {
        this.errors = FieldErrorOrStringList.fromErrorList(errorList);
    }

    /**
     * Set the errors of this error object from a list of strings.
     *
     * @param stringList The list of strings.
     */
    void setErrorsFromStringList(final List<String> stringList) {
        this.errors = FieldErrorOrStringList.fromStringList(stringList);
    }
}
