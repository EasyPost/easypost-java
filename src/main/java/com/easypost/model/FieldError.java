package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
@SuppressWarnings("JavaLangClash")
public final class FieldError {
    private String field;
    private String message;
    private String suggestion;

    /**
     * Set the field of this error object.
     *
     * @param field The field.
     */
    void setField(final String field) {
        this.field = field;
    }

    /**
     * Set the message of this error object.
     *
     * @param message The error message.
     */
    void setMessage(final String message) {
        this.message = message;
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
