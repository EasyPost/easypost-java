package com.easypost.exception.API;

import com.easypost.exception.EasyPostException;

public class TimeoutError extends EasyPostException {
    /**
     * TimeoutError constructor.
     *
     * @param message the exception message
     * @param code the exception code
     * @param statusCode the exception status code
     */
    public TimeoutError(final String message, final String code, final int statusCode) {
        super(message, code, statusCode);
    }
}
