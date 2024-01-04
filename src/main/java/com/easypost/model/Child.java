package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public class Child extends EasyPostResource {
    private String parentId;
    private String name;
    private String phoneNumber;
    private Boolean verified;
    private List<ApiKey> apiKeys; 
}
