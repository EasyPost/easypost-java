/**
 * Error.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

public final class Error {
    private String field;
    private String message;
    private String suggestion;
    private String code;

    /**
     * Get the field that caused the error.
     *
     * @return field
     */
    public String getField() {
        return field;
    }

    /**
     * Set the field that caused the error.
     *
     * @param field field
     */
    public void setField(final String field) {
        this.field = field;
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

    /**
     * Get the suggestion for the error.
     *
     * @return suggestion
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * Set the suggestion for the error.
     *
     * @param suggestion suggestion
     */
    public void setSuggestion(final String suggestion) {
        this.suggestion = suggestion;
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
}
