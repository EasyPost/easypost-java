package com.easypost.exception.API;

import com.easypost.exception.EasyPostException;

public class RateLimitError extends EasyPostException {
    /**
     * RateLimitError constructor.
     *
     * @param message the exception message
     * @param code the exception code
     * @param statusCode the exception status code
     */
    public RateLimitError(final String message, final String code, final int statusCode) {
        super(message, code, statusCode);
    }
}
