package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class WebhookCollection extends EasyPostResource {
    List<Webhook> webhooks;

    public List<Webhook> getWebhooks() {
        return webhooks;
    }
    public void setWebhooks(List<Webhook> webhooks) {
        this.webhooks = webhooks;
    }
}
