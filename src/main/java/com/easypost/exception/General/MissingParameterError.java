package com.easypost.exception.General;

import com.easypost.exception.EasyPostException;

public class MissingParameterError extends EasyPostException {
    /**
     * MissingParameterError constructor.
     *
     * @param message the exception message
     */
    public MissingParameterError(final String message) {
        super(message);
    }
}
