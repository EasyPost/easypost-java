package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class ApiKeys extends EasyPostResource {
    private List<ApiKey> keys;
    private List<ApiKeys> children;
}
