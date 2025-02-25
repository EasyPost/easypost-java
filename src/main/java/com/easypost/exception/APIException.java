package com.easypost.exception;

import com.easypost.model.FieldErrorOrStringList;
import com.easypost.model.Error;

public class APIException extends EasyPostException {
    private static final long serialVersionUID = 1L;
    private final String code;
    private final Integer statusCode;
    private final String message;
    private final FieldErrorOrStringList errors;

    /**
     * APIException constructor.
     *
     * @param message the exception message
     */
    public APIException(final String message) {
        this(message, null);
    }

    /**
     * APIException constructor.
     *
     * @param message the exception message
     * @param ex      the exception cause
     */
    public APIException(final String message, final Throwable ex) {
        this(message, null, null, null, ex);
    }

    /**
     * APIException constructor.
     *
     * @param message    the exception message
     * @param code       the exception code
     * @param statusCode the exception status code
     * @param errors     the errors array
     */
    public APIException(final String message, final String code, final int statusCode, final FieldErrorOrStringList errors) {
        this(message, code, statusCode, errors, null);
    }

    /**
     * APIException constructor.
     *
     * @param message    the exception message
     * @param code       the exception code
     * @param statusCode the exception status code
     * @param errors     the errors array
     * @param ex         the exception cause
     */
    public APIException(final String message, final String code, final Integer statusCode,
        final FieldErrorOrStringList errors, final Throwable ex) {
        super(message);
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
        this.errors = errors;
    }

    /**
     * Get status code of the error object.
     * 
     * @return statusCode the status code of the error object
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * Get code of the error object.
     *
     * @return code the code of the error object
     */
    public String getCode() {
        return code;
    }

    /**
     * Get message of the error object.
     *
     * @return message the message of the error object
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Get errors array of the exception.
     *
     * @return errors of the exception
     */
    public FieldErrorOrStringList getErrors() {
        return errors;
    }
}
