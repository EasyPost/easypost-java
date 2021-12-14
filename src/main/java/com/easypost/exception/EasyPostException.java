/**
 * EasyPostException.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.exception;

public class EasyPostException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String param;

    /**
     * Constructor
     *
     * @param message the exception message
     */
    public EasyPostException(String message) {
        super(message, null);
        this.param = null;
    }

    /**
     * Constructor
     *
     * @param message the exception message
     * @param e       the exception cause
     */
    public EasyPostException(String message, Throwable e) {
        super(message, e);
        this.param = null;
    }

    /**
     * Constructor
     *
     * @param message the exception message
     * @param param   the parameter name
     * @param e       the exception cause
     */
    public EasyPostException(String message, String param, Throwable e) {
        super(message, e);
        this.param = param;
    }

    /**
     * Get the parameter name
     *
     * @return the parameter name
     */
    public String getParam() {
        return param;
    }
}
