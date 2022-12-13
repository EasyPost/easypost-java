package com.easypost.exception.General;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;

public class MissingParameterError extends EasyPostException {
    /**
     * MissingParameterError constructor.
     *
     * @param parameterName the name of the missing parameter
     */
    public MissingParameterError(final String parameterName) {
        super(String.format(Constants.ErrorMessages.MISSING_REQUIRED_PARAMETER, parameterName));
    }
}
