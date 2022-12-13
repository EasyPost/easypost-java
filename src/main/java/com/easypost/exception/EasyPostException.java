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

    /**
     * EasyPostException constructor.
     *
     * @param message the exception message
     */
    public EasyPostException(final String message) {
        super(message);
    }
}
