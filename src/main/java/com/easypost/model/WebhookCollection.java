package com.easypost.model;

import java.util.List;

import lombok.Getter;

@Getter
public final class WebhookCollection extends EasyPostResource {
    private List<Webhook> webhooks;
}
