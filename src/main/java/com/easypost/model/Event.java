package com.easypost.model;

import java.util.List;
import java.util.Map;

public final class Event extends EasyPostResource {
    private String description;
    private Map<String, Object> result;
    private Map<String, Object> previousAttributes;
    private List<String> pendingUrls;
    private List<String> completedUrls;

    /**
     * Get the description of this Event.
     *
     * @return Event's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of this Event.
     *
     * @param description Event's description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get the previous attributes of this Event.
     *
     * @return Event's previous attributes
     */
    public Map<String, Object> getPreviousAttributes() {
        return previousAttributes;
    }

    /**
     * Set the previous attributes of this Event.
     *
     * @param previousAttributes Event's previous attributes
     */
    public void setPreviousAttributes(final Map<String, Object> previousAttributes) {
        this.previousAttributes = previousAttributes;
    }

    /**
     * Get the result of this Event.
     *
     * @return Event's result
     */
    public Map<String, Object> getResult() {
        return result;
    }

    /**
     * Set the result of this Event.
     *
     * @param result Event's result
     */
    public void setResult(final Map<String, Object> result) {
        this.result = result;
    }

    /**
     * Get the pendingUrls of this Event.
     *
     * @return Event's pendingUrls
     */
    public List<String> getPendingUrls() {
        return this.pendingUrls;
    }

    /**
     * 
     * @param pendingUrls pendingUrls of this Event
     */
    public void setPendingUrls(List<String> pendingUrls) {
        this.pendingUrls = pendingUrls;
    }

    /**
     * Get the completedUrls of this Event.
     *
     * @return Event's completedUrls
     */
    public List<String> getCompletedUrls() {
        return this.completedUrls;
    }

    /**
     *
     * @param completedUrls pendingUrls of this Event
     */
    public void setCompletedUrls(List<String> completedUrls) {
        this.completedUrls = completedUrls;
    }
}
