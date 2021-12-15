/**
 * ApiKey.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

public final class ApiKey extends EasyPostResource {
    private String mode;
    private String key;

    /**
     * Get the API key mode.
     *
     * @return API key mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the API key mode.
     *
     * @param mode API key mode
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Get the API key.
     *
     * @return API key
     */
    public String getKey() {
        return key;
    }

    /**
     * Set the API key.
     *
     * @param key API key
     */
    public void setKey(final String key) {
        this.key = key;
    }
}

