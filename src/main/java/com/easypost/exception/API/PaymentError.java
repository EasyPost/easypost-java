package com.easypost.exception.API;

import com.easypost.exception.APIException;

import java.util.List;

public class PaymentError extends APIException {
    /**
     * PaymentError constructor.
     *
     * @param message    the exception message
     * @param code       the exception code
     * @param errors     the errors array
     * @param statusCode the exception status code
     */
    public PaymentError(final String message, final String code, List<Object> errors, final int statusCode) {
        super(message, code, errors, statusCode);
    }
}
