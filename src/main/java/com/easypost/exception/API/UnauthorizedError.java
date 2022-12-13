package com.easypost.exception.API;

import java.util.List;

import com.easypost.model.Error;
import com.easypost.exception.APIException;

public class UnauthorizedError extends APIException {
    /**
     * UnauthorizedError constructor.
     *
     * @param message    the exception message
     * @param code       the exception code
     * @param statusCode the exception status code
     * @param errors     the errors array
     */
    public UnauthorizedError(final String message, final String code, final int statusCode, List<Error> errors) {
        super(message, code, statusCode, errors);
    }
}
