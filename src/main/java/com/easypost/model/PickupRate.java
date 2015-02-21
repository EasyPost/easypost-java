package com.easypost.model;

import com.easypost.net.EasyPostResource;

public class PickupRate extends EasyPostResource {
    public String id;
    String mode;
    CarrierAccount carrierAccount;
    String service;
    Float rate;
    String currency;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

    public CarrierAccount getCarrierAccount() { return carrierAccount; }
    public void setCarrierAccount(CarrierAccount carrierAccount) { this.carrierAccount = carrierAccount; }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    public Float getRate() { return rate; }
    public void setRate(Float rate) { this.rate = rate; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}