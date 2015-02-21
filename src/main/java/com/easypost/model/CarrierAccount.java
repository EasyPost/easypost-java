package com.easypost.model;

import com.easypost.net.EasyPostResource;

public class CarrierAccount extends EasyPostResource {
    public String id;
    String readable;
    String description;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getReadable() { return readable; }
    public void setReadable(String readable) { this.readable = readable; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}