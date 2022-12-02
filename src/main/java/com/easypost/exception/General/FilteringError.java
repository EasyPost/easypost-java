package com.easypost.exception.General;

import com.easypost.exception.EasyPostException;

public class FilteringError extends EasyPostException {
    /**
     * FilteringError constructor.
     *
     * @param message the exception message
     */
    public FilteringError(final String message) {
        super(message);
    }

    /**
     * FilteringError constructor with nested exception.
     *
     * @param message the exception message
     * @param e the nested exception
     */
    public FilteringError(final String message, Throwable e) {
        super(message, e);
    }
}
