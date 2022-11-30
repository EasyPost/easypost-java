package com.easypost.exception.API;

import com.easypost.exception.EasyPostException;

public class ExternalApiError extends EasyPostException {
    /**
     * ExternalApiError constructor.
     *
     * @param message the exception message
     */
    public ExternalApiError(final String message) {
        super(message);
    }
}
