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
}
