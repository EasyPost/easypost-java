package com.easypost.exception.General;

import com.easypost.exception.EasyPostException;

public class SignatureVerificationError extends EasyPostException {
    /**
     * SignatureVerificationError constructor.
     *
     * @param message the exception message
     */
    public SignatureVerificationError(final String message) {
        super(message);
    }

    /**
     * SignatureVerificationError constructor with nested exception.
     *
     * @param message the exception message
     * @param e the nested exception
     */
    public SignatureVerificationError(final String message, Throwable e) {
        super(message, e);
    }
}
