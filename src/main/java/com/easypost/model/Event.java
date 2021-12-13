/**
 * Event.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.Map;

public final class Event extends EasyPostResource {
    public String id;
    private String description;
    private String mode;
    private EasyPostResource result;
    private Map<String, Object> previousAttributes;


    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(final String mode) {
        this.mode = mode;
    }

    public EasyPostResource getResult() {
        return result;
    }

    public void setResult(final EasyPostResource result) {
        this.result = result;
    }

    public Map<String, Object> getPreviousAttributes() {
        return previousAttributes;
    }

    public void setPreviousAttributes(
            final Map<String, Object> previousAttributes) {
        this.previousAttributes = previousAttributes;
    }


    public static Event retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static EventCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return all(params, null);
    }

    public static Event retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Event.class, id), null,
                Event.class, apiKey);
    }

    public static EventCollection all(final Map<String, Object> params,
                                      final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Event.class), params,
                EventCollection.class, apiKey);
    }
}
