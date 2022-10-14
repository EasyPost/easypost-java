/**
 * EasyPostException.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.easypost.exception;

public class EasyPostException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String code;
    private final Integer statusCode;
    private final String message;

    /**
     * EasyPostException constructor.
     *
     * @param message the exception message
     */
    public EasyPostException(final String message) {
        super(message);
        this.code = null;
        this.statusCode = null;
        this.message = message;
    }

    /**
     * EasyPostException constructor.
     *
     * @param message the exception message
     * @param ex      the exception cause
     */
    public EasyPostException(final String message, final Throwable ex) {
        super(message, ex);
        this.code = null;
        this.statusCode = null;
        this.message = message;
    }

    /**
     * EasyPostException constructor.
     *
     * @param message the exception message
     * @param code the exception code
     * @param statusCode the exception status code
     */
    public EasyPostException(final String message, final String code, final int statusCode) {
        super(message);
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
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
}
