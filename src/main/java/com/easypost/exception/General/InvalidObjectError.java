package com.easypost.exception.General;

import com.easypost.exception.EasyPostException;

public class InvalidObjectError extends EasyPostException {
    /**
     * InvalidObjectError constructor.
     *
     * @param message the exception message
     */
    public InvalidObjectError(final String message) {
        super(message);
    }
}
