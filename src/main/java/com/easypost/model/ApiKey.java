package com.easypost.model;

import lombok.Getter;

@Getter
public final class ApiKey extends EasyPostResource {
    private String key;
    private Boolean active;
}
