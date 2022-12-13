package com.easypost.exception.API;

import com.easypost.exception.APIException;

public class ExternalApiError extends APIException {
    /**
     * ExternalApiError constructor.
     *
     * @param message the exception message
     */
    public ExternalApiError(final String message) {
        super(message);
    }
}
