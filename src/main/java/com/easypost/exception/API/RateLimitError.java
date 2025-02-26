package com.easypost.exception.API;

import com.easypost.model.FieldErrorOrStringList;
import com.easypost.exception.APIException;

public class RateLimitError extends APIException {
    /**
     * RateLimitError constructor.
     *
     * @param message    the exception message
     * @param code       the exception code
     * @param errors     the errors array
     * @param statusCode the exception status code
     */
    public RateLimitError(final String message, final String code, FieldErrorOrStringList errors, final int statusCode) {
        super(message, code, errors, statusCode);
    }
}
