package com.easypost.exception.API;

import com.easypost.exception.EasyPostException;

public class HttpError extends EasyPostException {
    /**
     * HttpError constructor.
     *
     * @param message the exception message
     */
    public HttpError(final String message) {
        super(message);
    }

    /**
     * HttpError constructor with nested exception.
     *
     * @param message the exception message
     * @param e the nested exception
     */
    public HttpError(final String message, Throwable e) {
        super(message, e);
    }
}
