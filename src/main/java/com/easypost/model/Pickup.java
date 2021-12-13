/**
 * Pickup.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Pickup extends EasyPostResource {
    public String id;
    private String mode;
    private String status;
    private String reference;
    private Date minDatetime;
    private Date maxDatetime;
    private Boolean isAccountAddress;
    private String instructions;
    private List<ShipmentMessage> messages;
    private String confirmation;
    private Address address;
    private List<CarrierAccount> carrierAccounts;
    private List<PickupRate> pickupRates;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(final String mode) {
        this.mode = mode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(final String reference) {
        this.reference = reference;
    }

    public Date getMinDatetime() {
        return minDatetime;
    }

    public void setMinDatetime(final Date minDatetime) {
        this.minDatetime = minDatetime;
    }

    public Date getMaxDatetime() {
        return maxDatetime;
    }

    public void setMaxDatetime(final Date maxDatetime) {
        this.maxDatetime = maxDatetime;
    }

    public Boolean getIsAccountAddress() {
        return isAccountAddress;
    }

    public void setIsAccountAddress(final Boolean isAccountAddress) {
        this.isAccountAddress = isAccountAddress;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(final String instructions) {
        this.instructions = instructions;
    }

    public List<ShipmentMessage> getMessages() {
        return messages;
    }

    public void setMessages(final List<ShipmentMessage> messages) {
        this.messages = messages;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(final String confirmation) {
        this.confirmation = confirmation;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public List<CarrierAccount> getCarrierAccounts() {
        return carrierAccounts;
    }

    public void setCarrierAccounts(final List<CarrierAccount> carrierAccounts) {
        this.carrierAccounts = carrierAccounts;
    }

    public List<PickupRate> getPickupRates() {
        return pickupRates;
    }

    public void setPickupRates(final List<PickupRate> pickupRates) {
        this.pickupRates = pickupRates;
    }


    // create
    public static Pickup create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static Pickup create(final Map<String, Object> params,
                                final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("pickup", params);

        return request(RequestMethod.POST, classURL(Pickup.class),
                wrappedParams, Pickup.class, apiKey);
    }

    // retrieve
    public static Pickup retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static Pickup retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Pickup.class, id), null,
                Pickup.class, apiKey);
    }

    // refresh
    public Pickup refresh() throws EasyPostException {
        return this.refresh(null, null);
    }

    public Pickup refresh(final Map<String, Object> params)
            throws EasyPostException {
        return this.refresh(params, null);
    }

    public Pickup refresh(final String apiKey) throws EasyPostException {
        return this.refresh((Map<String, Object>) null, apiKey);
    }

    public Pickup refresh(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET,
                String.format("%s", instanceURL(Pickup.class, this.getId())),
                params, Pickup.class, apiKey);
    }

    // buy
    public Pickup buy() throws EasyPostException {
        return this.buy(null, null);
    }

    public Pickup buy(final Map<String, Object> params)
            throws EasyPostException {
        return this.buy(params, null);
    }

    public Pickup buy(final String apiKey) throws EasyPostException {
        return this.buy((Map<String, Object>) null, apiKey);
    }

    public Pickup buy(final PickupRate pickupRate) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rate", pickupRate);

        return this.buy(params, null);
    }

    public Pickup buy(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        Pickup response = request(RequestMethod.POST, String.format("%s/buy",
                        instanceURL(Pickup.class, this.getId())), params, Pickup.class,
                apiKey);

        this.merge(this, response);
        return this;
    }

    // cancel
    public Pickup cancel() throws EasyPostException {
        return this.cancel(null, null);
    }

    public Pickup cancel(final Map<String, Object> params)
            throws EasyPostException {
        return this.cancel(params, null);
    }

    public Pickup cancel(final String apiKey) throws EasyPostException {
        return this.cancel((Map<String, Object>) null, apiKey);
    }

    public Pickup cancel(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.POST, String.format("%s/cancel",
                        instanceURL(Pickup.class, this.getId())), params, Pickup.class,
                apiKey);
    }
}
