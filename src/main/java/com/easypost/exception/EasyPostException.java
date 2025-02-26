package com.easypost.exception;

public class EasyPostException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * EasyPostException constructor.
     *
     * @param message the exception message
     */
    public EasyPostException(final String message) {
        super(message);
    }
}
