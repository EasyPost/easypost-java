package com.easypost.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Fields extends EasyPostResource {
    private Map<String, Field> credentials;
    private Map<String, Field> testCredentials;
    private boolean autoLink;
    private boolean customWorkflow;
}
