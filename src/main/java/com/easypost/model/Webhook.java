package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Webhook extends EasyPostResource {
    private String id;
    private String mode;
    private String url;
    private Date disabledAt;

    /**
     * Create a Webhook object from a map of parameters.
     *
     * @param params the map of parameters
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public static Webhook create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a Webhook object from a map of parameters.
     *
     * @param params the map of parameters
     * @param apiKey API key to use in request (overrides default API key).
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public static Webhook create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("webhook", params);

        return request(RequestMethod.POST, classURL(Webhook.class), wrappedParams, Webhook.class, apiKey);
    }

    /**
     * Retrieve a Webhook object from the API.
     *
     * @param id the ID of the Webhook to retrieve
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public static Webhook retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Webhook object from the API.
     *
     * @param id     the ID of the Webhook to retrieve
     * @param apiKey API key to use in request (overrides default API key).
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public static Webhook retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Webhook.class, id), null, Webhook.class, apiKey);
    }

    /**
     * Get a list of all Webhook objects.
     *
     * @return WebhookCollection object
     * @throws EasyPostException when the request fails.
     */
    public static WebhookCollection all() throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        return all(params, null);
    }

    /**
     * Get a list of all Webhook objects.
     *
     * @param params params for request
     * @return WebhookCollection object
     * @throws EasyPostException when the request fails.
     */
    public static WebhookCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * Get a list of all Webhook objects.
     *
     * @param params params for request
     * @param apiKey API key to use in request (overrides default API key).
     * @return WebhookCollection object
     * @throws EasyPostException when the request fails.
     */
    public static WebhookCollection all(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Webhook.class), params, WebhookCollection.class, apiKey);
    }

    /**
     * Get the ID of the webhook.
     *
     * @return the ID of the webhook
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the webhook.
     *
     * @param id the ID of the webhook
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the mode of the webhook.
     *
     * @return the mode of the webhook
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the mode of the webhook.
     *
     * @param mode the mode of the webhook
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Get the URL of the webhook.
     *
     * @return the URL of the webhook
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the URL of the webhook.
     *
     * @param url the URL of the webhook
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Get the date and time when the webhook was disabled.
     *
     * @return the date and time when the webhook was disabled
     */
    public Date getDisabledAt() {
        return disabledAt;
    }

    /**
     * Set the date and time when the webhook was disabled.
     *
     * @param disabledAt the date and time when the webhook was disabled
     */
    public void setDisabledAt(final Date disabledAt) {
        this.disabledAt = disabledAt;
    }

    /**
     * Delete this Webhook.
     *
     * @throws EasyPostException when the request fails.
     */
    public void delete() throws EasyPostException {
        this.delete(null);
    }

    /**
     * Delete this Webhook.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @throws EasyPostException when the request fails.
     */
    public void delete(final String apiKey) throws EasyPostException {
        request(RequestMethod.DELETE, instanceURL(Webhook.class, this.getId()), null, Webhook.class, apiKey);
    }

    /**
     * Update this webhook.
     *
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook update() throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        return this.update(params, null);
    }

    /**
     * Update this webhook.
     *
     * @param params the map of parameters
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook update(final Map<String, Object> params) throws EasyPostException {
        return this.update(params, null);
    }

    /**
     * Update this webhook.
     *
     * @param params the map of parameters
     * @param apiKey API key to use in request (overrides default API key).
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook update(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("webhook", params);

        Webhook response =
                request(RequestMethod.PUT, instanceURL(Webhook.class, this.getId()), wrappedParams, Webhook.class,
                        apiKey);

        this.merge(this, response);
        return this;
    }
}
