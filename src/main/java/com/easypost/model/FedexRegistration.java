package com.easypost.model;

import java.util.Map;
import lombok.Getter;

@Getter
public final class FedexRegistration extends EasyPostResource {
    private String type;
    private Map<String, String> credentials;
}
