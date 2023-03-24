package com.easypost.exception.General;

import com.easypost.exception.EasyPostException;

public class EndOfPaginationError extends EasyPostException {
    /**
     * EndOfPaginationError constructor.
     */
    public EndOfPaginationError() {
        super("There are no more pages to retrieve.");
    }
}
