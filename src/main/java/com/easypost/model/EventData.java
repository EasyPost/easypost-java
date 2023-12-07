package com.easypost.model;

import java.util.Map;
import lombok.Getter;

@Getter
public final class EventData extends EasyPostResource {
    private Map<String, Object> previousAttributes;
}
