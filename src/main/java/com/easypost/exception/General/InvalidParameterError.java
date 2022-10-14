package com.easypost.exception.General;

import com.easypost.exception.EasyPostException;

public class InvalidParameterError extends EasyPostException{
    /**
     * InvalidParameterError constructor.
     *
     * @param message the exception message
     */
    public InvalidParameterError(final String message) {
        super(message);
    }

    /**
     * constructor.
     *
     * @param message the exception message
     * @param e the Throwable object
     */
    public InvalidParameterError(final String message, Throwable e) {
        super(message, e);
    }
}
