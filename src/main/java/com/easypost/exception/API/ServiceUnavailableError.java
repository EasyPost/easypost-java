package com.easypost.exception.API;

import com.easypost.model.FieldErrorOrStringList;
import com.easypost.exception.APIException;

public class ServiceUnavailableError extends APIException {
    /**
     * ServiceUnavailablError constructor.
     *
     * @param message    the exception message
     * @param code       the exception code
     * @param errors     the errors array
     * @param statusCode the exception status code
     */
    public ServiceUnavailableError(final String message, final String code, FieldErrorOrStringList errors, final int statusCode) {
        super(message, code, errors, statusCode);
    }
}
