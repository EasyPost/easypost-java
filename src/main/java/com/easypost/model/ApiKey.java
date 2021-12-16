package com.easypost.model;

import com.easypost.model.enums.Mode;
import com.easypost.net.EasyPostResource;

public class ApiKey extends EasyPostResource {
    Mode mode;
    String key;

    public Mode getMode() { return mode; }
    public void setMode(Mode mode) { this.mode = mode; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
}

