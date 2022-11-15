package com.easypost.model;

import java.util.Date;

public final class Webhook extends EasyPostResource {
    private String url;
    private Date disabledAt;

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
}
