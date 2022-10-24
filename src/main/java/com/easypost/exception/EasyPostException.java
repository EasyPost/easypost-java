/**
 * EasyPostException.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.easypost.exception;

import com.easypost.model.Error;

import java.util.List;

public class EasyPostException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String code;
    private final Integer statusCode;
    private final String message;
    private final List<Error> errors;

    /**
     * EasyPostException constructor.
     *
     * @param message the exception message
     */
    public EasyPostException(final String message) {
        this(message, null);
    }

    /**
     * EasyPostException constructor.
     *
     * @param message the exception message
     * @param ex      the exception cause
     */
    public EasyPostException(final String message, final Throwable ex) {
        this(message, null, null, null, ex);
    }

    /**
     * EasyPostException constructor.
     *
     * @param message    the exception message
     * @param code       the exception code
     * @param statusCode the exception status code
     * @param errors     the errors array
     */
    public EasyPostException(final String message, final String code, final int statusCode, final List<Error> errors) {
        this(message, code, statusCode, errors, null);
    }

    /**
     * EasyPostException constructor.
     *
     * @param message    the exception message
     * @param code       the exception code
     * @param statusCode the exception status code
     * @param errors     the errors array
     * @param ex         the exception cause
     */
    public EasyPostException(final String message, final String code, final Integer statusCode,
        final List<Error> errors, final Throwable ex) {
        super(message, ex);
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
    public String getMessage() {
        return message;
    }

    /**
     * Get errors array of the exception.
     *
     * @return errors of the exception
     */
    public List<Error> getErrors() {
        return errors;
    }
}
