package com.easypost.exception.API;

import com.easypost.exception.EasyPostException;

public class UnknownApiError extends EasyPostException {
    /**
     * UnknownApiError constructor.
     *
     * @param message the exception message
     * @param code the exception code
     * @param statusCode the exception status code
     */
    public UnknownApiError(final String message, final String code, final int statusCode) {
        super(message, code, statusCode);
    }
}
