package com.easypost.model;

import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public final class Event extends EasyPostResource {
    private String description;
    private Map<String, Object> result;
    private Map<String, Object> previousAttributes;
    private List<String> pendingUrls;
    private List<String> completedUrls;
}
