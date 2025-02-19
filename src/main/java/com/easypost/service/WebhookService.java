package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Webhook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class WebhookService {
    private final EasyPostClient client;

    /**
     * WebhookService constructor.
     *
     * @param client The client object.
     */
    WebhookService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a Webhook object from a map of parameters.
     *
     * @param params the map of parameters
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("webhook", params);

        String endpoint = "webhooks";

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, Webhook.class, client);
    }

    /**
     * Retrieve a Webhook object from the API.
     *
     * @param id the ID of the Webhook to retrieve
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook retrieve(final String id) throws EasyPostException {
        String endpoint = "webhooks/" + id;

        return Requestor.request(RequestMethod.GET, endpoint, null, Webhook.class, client);
    }

    /**
     * Get a list of all Webhook objects.
     *
     * @return List of Webhook objects.
     * @throws EasyPostException when the request fails.
     */
    public List<Webhook> all() throws EasyPostException {
        return all(null);
    }

    /**
     * Get a list of all Webhook objects.
     *
     * @param params params for request
     * @return List of Webhook objects.
     * @throws EasyPostException when the request fails.
     */
    public List<Webhook> all(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "webhooks";

        Webhook[] response = Requestor.request(RequestMethod.GET, endpoint, params, Webhook[].class, client);
        return Arrays.asList(response); 
    }

    /**
     * Delete this Webhook.
     *
     * @param id The ID of webhook.
     * @throws EasyPostException when the request fails.
     */
    public void delete(final String id) throws EasyPostException {
        String endpoint = "webhooks/" + id;

        Requestor.request(RequestMethod.DELETE, endpoint, null, Webhook.class, client);
    }

    /**
     * Update this webhook.
     *
     * @param id The ID of webhook.
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook update(final String id) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        return this.update(id, params);
    }

    /**
     * Update this webhook.
     *
     * @param id     The ID of webhook.
     * @param params Map of parameters
     * @return Webhook object
     * @throws EasyPostException when the request fails.
     */
    public Webhook update(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "webhooks/" + id;

        return Requestor.request(RequestMethod.PUT, endpoint, params, Webhook.class, client);
    }
}
