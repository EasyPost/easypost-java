package com.easypost.exception.General;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;

public class EndOfPaginationError extends EasyPostException {
    /**
     * EndOfPaginationError constructor.
     */
    public EndOfPaginationError() {
        super(Constants.ErrorMessages.NO_MORE_PAGES_TO_RETRIEVE);
    }
}
