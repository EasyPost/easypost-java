package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public class WebhookCollection extends EasyPostResource {
    List<Webhook> webhooks;

    public List<Webhook> getWebhooks() {
        return webhooks;
    }
    public void setWebhooks(List<Webhook> webhooks) {
        this.webhooks = webhooks;
    }
}
