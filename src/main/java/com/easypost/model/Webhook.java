/**
 * Webhook.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Webhook extends EasyPostResource {
    public String id;
    private String mode;
    private String url;
    private Date disabledAt;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(final String mode) {
        this.mode = mode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Date getDisabledAt() {
        return disabledAt;
    }

    public void setDisabledAt(final Date disabledAt) {
        this.disabledAt = disabledAt;
    }

    // create
    public static Webhook create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static Webhook create(final Map<String, Object> params,
                                 String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("webhook", params);

        return request(RequestMethod.POST, classURL(Webhook.class),
                wrappedParams, Webhook.class, apiKey);
    }

    // retrieve
    public static Webhook retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static Webhook retrieve(final String id, String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Webhook.class, id), null,
                Webhook.class, apiKey);
    }

    // all
    public static WebhookCollection all() throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        return all(params, null);
    }

    public static WebhookCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return all(params, null);
    }

    public static WebhookCollection all(final Map<String, Object> params,
                                        final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Webhook.class), params,
                WebhookCollection.class, apiKey);
    }

    // delete
    public void delete() throws EasyPostException {
        this.delete(null);
    }

    public void delete(final String apiKey) throws EasyPostException {
        request(RequestMethod.DELETE, instanceURL(Webhook.class, this.getId()),
                null, Webhook.class, apiKey);
    }

    // update
    public Webhook update() throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        return this.update(params, null);
    }

    public Webhook update(final Map<String, Object> params)
            throws EasyPostException {
        return this.update(params, null);
    }

    public Webhook update(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("webhook", params);

        Webhook response = request(RequestMethod.PUT,
                instanceURL(Webhook.class, this.getId()), wrappedParams,
                Webhook.class, apiKey);

        this.merge(this, response);
        return this;
    }
}
