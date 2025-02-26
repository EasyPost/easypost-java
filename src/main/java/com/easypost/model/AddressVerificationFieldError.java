package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class AddressVerificationFieldError {
    private String message;
    private String code;
    private String field;
    private String suggestion;

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
     * @param code The code.
     */
    void setCode(final String code) {
        this.code = code;
    }

    /**
     * Set the field of this error object.
     *
     * @param field The field.
     */
    void setField(final String field) {
        this.field = field;
    }

    /**
     * Set the suggestion of this error object.
     *
     * @param suggestion The suggestion.
     */
    void setSuggestion(final String suggestion) {
        this.suggestion = suggestion;
    }
}
