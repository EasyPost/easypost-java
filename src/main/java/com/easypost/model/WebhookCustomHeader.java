package com.easypost.model;

import lombok.Getter;

@Getter
public final class WebhookCustomHeader extends EasyPostResource {
    private String name;
    private String value;
}
