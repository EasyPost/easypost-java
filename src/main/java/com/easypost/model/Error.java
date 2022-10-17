package com.easypost.model;

import java.util.List;

public final class Error {
    private String message;
    private String code;
    private List<Error> errors;

    /**
     * Get the errors from an Error object.
     *
     * @return errors
     */
    public List<Error> getError() {
        return errors;
    }

    /**
     * Set the errors for an Error object.
     * 
     * @param errors errors
     */
    public void setError(List<Error> errors) {
        this.errors = errors;
    }

    /**
     * Get the error code.
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the error code.
     *
     * @param code code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Get the error message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the error message.
     *
     * @param message message
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
