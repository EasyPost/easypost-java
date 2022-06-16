package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ApiKeys extends EasyPostResource {
    private String id;
    private List<ApiKey> keys;
    private List<ApiKeys> children;

    /**
     * Get API key ID.
     *
     * @return API key ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Set API key ID.
     *
     * @param id API key ID.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get a list of API keys.
     *
     * @return List of ApiKey objects.
     */
    public List<ApiKey> getKeys() {
        return keys;
    }

    /**
     * Set list of API keys.
     *
     * @param keys List of ApiKey objects.
     */
    public void setKeys(final List<ApiKey> keys) {
        this.keys = keys;
    }

    /**
     * Get a list of API keys for a given parent key.
     *
     * @return List of ApiKeys objects.
     */
    public List<ApiKeys> getChildren() {
        return children;
    }

    /**
     * Set a list of API keys for a given parent key.
     *
     * @param children List of ApiKeys objects.
     */
    public void setChildren(final List<ApiKeys> children) {
        this.children = children;
    }

    /**
     * Get all API keys.
     *
     * @return ApiKeys object.
     * @throws EasyPostException when the request fails.
     */
    public static ApiKeys all() throws EasyPostException {
        return all(null);
    }

    /**
     * Get all API keys.
     *
     * @param apiKey API key to use for this request.
     * @return ApiKeys object.
     * @throws EasyPostException when the request fails.
     */
    public static ApiKeys all(final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, classURL(ApiKey.class), null, ApiKeys.class, apiKey);
    }

}


