package com.easypost.model;

import com.easypost.model.enums.Mode;
import com.easypost.net.EasyPostResource;

public class PickupRate extends EasyPostResource {
    public String id;
    Mode mode;
    String carrier;
    String service;
    Float rate;
    String currency;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Mode getMode() { return mode; }
    public void setMode(Mode mode) { this.mode = mode; }

    public String getCarrier() { return carrier; }
    public void setCarrier(String carrier) { this.carrier = carrier; }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    public Float getRate() { return rate; }
    public void setRate(Float rate) { this.rate = rate; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
