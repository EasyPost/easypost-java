package com.easypost.exception;

import java.util.List;

public class APIException extends EasyPostException {
    private static final long serialVersionUID = 1L;
    private final String code;
    private final Integer statusCode;
    private final String message;
    private final List<Object> errors;

    /**
     * APIException constructor.
     *
     * @param message the exception message
     */
    public APIException(final String message) {
        this(message, null, null, null, null);
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
     * @param errors     the errors array
     */
    public APIException(final String message, final String code, final List<Object> errors) {
        this(message, code, errors, null, null);
    }

    /**
     * APIException constructor.
     *
     * @param message    the exception message
     * @param code       the exception code
     * @param errors     the errors array
     * @param statusCode the exception status code
     */
    public APIException(final String message, final String code, final List<Object> errors, final Integer statusCode) {
        this(message, code, errors, statusCode, null);
    }

    /**
     * APIException constructor.
     *
     * @param message    the exception message
     * @param code       the exception code
     * @param errors     the errors array
     * @param statusCode the exception status code
     * @param ex         the exception cause
     */
    public APIException(final String message, final String code, final List<Object> errors, final Integer statusCode, final Throwable ex) {
        super(message);
        this.message = message;
        this.code = code;
        this.errors = errors;
        this.statusCode = statusCode;
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
    public List<Object> getErrors() {
        return errors;
    }
}
