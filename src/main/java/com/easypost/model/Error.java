package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
@SuppressWarnings("JavaLangClash")
public final class Error {
    private String message;
    private String code;
    private List<Error> errors;
    private String suggestion;
    private String field;

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
    void setErrors(final List<Error> errors) {
        this.errors = errors;
    }

    /**
     * Set the suggestion of this error object.
     *
     * @param suggestion The suggestion.
     */
    void setSuggestion(final String suggestion) {
        this.suggestion = suggestion;
    }

    /**
     * Set the field of this error object.
     *
     * @param field The field.
     */
    void setField(final String field) {
        this.field = field;
    }
}
