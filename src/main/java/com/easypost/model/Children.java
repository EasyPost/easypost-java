package com.easypost.model;

import java.util.List;

public class Children extends EasyPostResource {
    private String parentId;
    private String name;
    private String phoneNumber;
    private Boolean verified;
    private Boolean defaultCarbonOffset;
    private List<ApiKey> apiKeys; 
}
