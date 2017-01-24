package com.easypost.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Webhook extends EasyPostResource {
    public String id;
    String mode;
    String url;
    Date disabledAt;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDisabledAt() {
        return disabledAt;
    }
    public void setDisabledAt(Date disabledAt) {
        this.disabledAt = disabledAt;
    }

    // create
    public static Webhook create(Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }
    public static Webhook create(Map<String, Object> params, String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("webhook", params);

        return request(RequestMethod.POST, classURL(Webhook.class), wrappedParams, Webhook.class, apiKey);
    }

    // retrieve
    public static Webhook retrieve(String id) throws EasyPostException {
        return retrieve(id, null);
    }
    public static Webhook retrieve(String id, String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Webhook.class, id), null, Webhook.class, apiKey);
    }

    // all
    public static WebhookCollection all() throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        return all(params, null);
    }
    public static WebhookCollection all(Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }
    public static WebhookCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, classURL(Webhook.class), params, WebhookCollection.class, apiKey);
    }

    // delete
    public void delete() throws EasyPostException {
        this.delete(null);
    }
    public void delete(String apiKey) throws EasyPostException {
        request(RequestMethod.DELETE, instanceURL(Webhook.class, this.getId()), null, Webhook.class, apiKey);
    }

    // update
    public Webhook update() throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        return this.update(params, null);
    }
    public Webhook update(Map<String, Object> params) throws EasyPostException {
        return this.update(params, null);
    }
    public Webhook update(Map<String, Object> params, String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("webhook", params);

        Webhook response = request(RequestMethod.PUT, instanceURL(Webhook.class, this.getId()), wrappedParams, Webhook.class, apiKey);

        this.merge(this, response);
        return this;
    }
}