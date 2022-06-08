/**
 * EasyPostException.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.easypost.exception;

public class EasyPostException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String param;

    /**
     * Constructor.
     *
     * @param message the exception message
     */
    public EasyPostException(final String message) {
        super(message, null);
        this.param = null;
    }

    /**
     * Constructor.
     *
     * @param message the exception message
     * @param ex      the exception cause
     */
    public EasyPostException(final String message, final Throwable ex) {
        super(message, ex);
        this.param = null;
    }

    /**
     * Constructor.
     *
     * @param message the exception message
     * @param param   the parameter name
     * @param ex      the exception cause
     */
    public EasyPostException(final String message, final String param, final Throwable ex) {
        super(message, ex);
        this.param = param;
    }

    /**
     * Get the parameter name.
     *
     * @return the parameter name
     */
    public String getParam() {
        return param;
    }
}
