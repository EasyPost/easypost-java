package com.easypost.model;

import java.util.Date;
import java.util.List;
import lombok.Getter;

@Getter
public final class Webhook extends EasyPostResource {
    private String url;
    private Date disabledAt;
    private List<WebhookCustomHeader> customHeaders;
}
