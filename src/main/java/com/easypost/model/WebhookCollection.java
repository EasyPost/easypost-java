package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class WebhookCollection extends EasyPostResource {
    private List<Webhook> webhooks;

    /**
     * Get this WebhookCollection's Webhook objects.
     *
     * @return List of Webhook objects.
     */
    public List<Webhook> getWebhooks() {
        return webhooks;
    }

    /**
     * Set this WebhookCollection's Webhook objects.
     *
     * @param webhooks List of Webhook objects.
     */
    public void setWebhooks(final List<Webhook> webhooks) {
        this.webhooks = webhooks;
    }
}
