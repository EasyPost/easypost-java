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
}
