package com.easypost.model;

public final class ApiKey extends EasyPostResource {
    private String key;

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

