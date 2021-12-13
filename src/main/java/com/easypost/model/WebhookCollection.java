/**
 * WebhookCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class WebhookCollection extends EasyPostResource {
    private List<Webhook> webhooks;

    public List<Webhook> getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(final List<Webhook> webhooks) {
        this.webhooks = webhooks;
    }
}
