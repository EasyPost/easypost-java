package com.easypost.model;

import java.util.List;

public final class Error {
    private String message;
    private String code;
    private List<Error> errors;
    private String suggestion;
    private String field;

    /**
     * Get the field of the error.
     *
     * @return field
     */
    public String getField() {
        return field;
    }

    /**
     * Set the field of the error.
     *
     * @param field field
     */
    public void setField(final String field) {
        this.field = field;
    }

    /**
     * Get the suggestion of the error.
     *
     * @return suggestion
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * Set the suggestion of the error.
     *
     * @param suggestion suggestion
     */
    public void setSuggestion(final String suggestion) {
        this.suggestion = suggestion;
    }

    /**
     * Get the errors from an Error object.
     *
     * @return errors
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * Set the errors for an Error object.
     * 
     * @param errors errors
     */
    public void setErrors(List<Error> errors) {
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
