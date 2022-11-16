package com.easypost.model;

import java.util.Map;
import lombok.Getter;

@Getter
public final class CarrierType extends EasyPostResource {
    private String type;
    private String readable;
    private String logo;
    private Map<String, Object> fields;
}
