package com.easypost.exception.General;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;

public class InvalidParameterError extends EasyPostException{
    /**
     * InvalidParameterError constructor.
     *
     * @param parameterName the name of the invalid parameter
     */
    public InvalidParameterError(final String parameterName) {
        super(String.format(Constants.ErrorMessages.INVALID_PARAMETER, parameterName));
    }

    /**
     * InvalidParameterError constructor with nested exception.
     *
     * @param parameterName the name of the invalid parameter
     * @param e the nested exception
     */
    public InvalidParameterError(final String parameterName, Throwable e) {
        super(String.format(Constants.ErrorMessages.INVALID_PARAMETER, parameterName), e);
    }
}
