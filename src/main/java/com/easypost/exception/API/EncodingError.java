package com.easypost.exception.API;

import com.easypost.exception.APIException;

public class EncodingError extends APIException {
    /**
     * EncodingError constructor.
     *
     * @param message the exception message
     */
    public EncodingError(final String message) {
        super(message);
    }

    /**
     * EncodingError constructor with nested exception.
     *
     * @param message the exception message
     * @param e the nested exception
     */
    public EncodingError(final String message, Throwable e) {
        super(message, e);
    }
}
