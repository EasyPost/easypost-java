package com.easypost.exception.API;

import com.easypost.exception.EasyPostException;

public class JsonError extends EasyPostException {
    /**
     * JsonError constructor.
     *
     * @param message the exception message
     */
    public JsonError(final String message) {
        super(message);
    }

    /**
     * JsonError constructor with nested exception.
     *
     * @param message the exception message
     * @param e the nested exception
     */
    public JsonError(final String message, Throwable e) {
        super(message, e);
    }
}
