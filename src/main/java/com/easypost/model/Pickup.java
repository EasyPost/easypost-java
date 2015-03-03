package com.easypost.model;

import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Pickup extends EasyPostResource {
    public String id;
    String mode;
    String status;
    String reference;
    Date minDatetime;
    Date maxDatetime;
    Boolean isAccountAddress;
    String instructions;
    List<ShipmentMessage> messages;
    String confirmation;
    Address address;
    List<CarrierAccount> carrierAccounts;
    List<PickupRate> pickupRates;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public Date getMinDatetime() { return minDatetime; }
    public void setMinDatetime(Date minDatetime) { this.minDatetime = minDatetime; }

    public Date getMaxDatetime() { return maxDatetime; }
    public void setMaxDatetime(Date maxDatetime) { this.maxDatetime = maxDatetime; }

    public Boolean getIsAccountAddress() { return isAccountAddress; }
    public void setIsAccountAddress(Boolean isAccountAddress) { this.isAccountAddress = isAccountAddress; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public List<ShipmentMessage> getMessages() { return messages; }
    public void setMessages(List<ShipmentMessage> messages) { this.messages = messages; }

    public String getConfirmation() { return confirmation; }
    public void setConfirmation(String confirmation) { this.confirmation = confirmation; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public List<CarrierAccount> getCarrierAccounts() { return carrierAccounts; }
    public void setCarrierAccounts(List<CarrierAccount> carrierAccounts) { this.carrierAccounts = carrierAccounts; }

    public List<PickupRate> getPickupRates() { return pickupRates; }
    public void setPickupRates(List<PickupRate> pickupRates) { this.pickupRates = pickupRates; }


    // create
    public static Pickup create(Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }
    public static Pickup create(Map<String, Object> params, String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("pickup", params);

        return request(RequestMethod.POST, classURL(Pickup.class), wrappedParams, Pickup.class, apiKey);
    }

    // retrieve
    public static Pickup retrieve(String id) throws EasyPostException {
        return retrieve(id, null);
    }
    public static Pickup retrieve(String id, String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Pickup.class, id), null, Pickup.class, apiKey);
    }

    // all
    public static PickupCollection all(Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }
    public static PickupCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, classURL(Pickup.class), params, PickupCollection.class, apiKey);
    }

    // refresh
    public Pickup refresh() throws EasyPostException {
        return this.refresh(null, null);
    }
    public Pickup refresh(Map<String, Object> params) throws EasyPostException {
        return this.refresh(params, null);
    }
    public Pickup refresh(String apiKey) throws EasyPostException {
        return this.refresh((Map<String, Object>) null, apiKey);
    }
    public Pickup refresh(Map<String, Object> params, String apiKey) throws EasyPostException {
        return request(
                RequestMethod.GET,
                String.format("%s", instanceURL(Pickup.class, this.getId())), params, Pickup.class, apiKey);
    }

    // buy
    public Pickup buy() throws EasyPostException {
        return this.buy(null, null);
    }
    public Pickup buy(Map<String, Object> params) throws EasyPostException {
        return this.buy(params, null);
    }
    public Pickup buy(String apiKey) throws EasyPostException {
        return this.buy((Map<String, Object>) null, apiKey);
    }
    public Pickup buy(PickupRate pickupRate) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rate", pickupRate);

        return this.buy(params, null);
    }
    public Pickup buy(Map<String, Object> params, String apiKey) throws EasyPostException {
        Pickup response = request(
                RequestMethod.POST,
                String.format("%s/buy", instanceURL(Pickup.class, this.getId())), params, Pickup.class, apiKey);

        this.merge(this, response);
        return this;
    }

    // cancel
    public Pickup cancel() throws EasyPostException {
        return this.cancel(null, null);
    }
    public Pickup cancel(Map<String, Object> params) throws EasyPostException {
        return this.cancel(params, null);
    }
    public Pickup cancel(String apiKey) throws EasyPostException {
        return this.cancel((Map<String, Object>) null, apiKey);
    }
    public Pickup cancel(Map<String, Object> params, String apiKey) throws EasyPostException {
        return request(
                RequestMethod.POST,
                String.format("%s/cancel", instanceURL(Pickup.class, this.getId())), params, Pickup.class, apiKey);
    }
}