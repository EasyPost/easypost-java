package com.easypost.exception.API;

import com.easypost.model.FieldErrorOrStringList;
import com.easypost.exception.APIException;

public class PaymentError extends APIException {
    /**
     * PaymentError constructor.
     *
     * @param message    the exception message
     * @param code       the exception code
     * @param statusCode the exception status code
     * @param errors     the errors array
     */
    public PaymentError(final String message, final String code, final int statusCode, FieldErrorOrStringList errors) {
        super(message, code, statusCode, errors);
    }
}
