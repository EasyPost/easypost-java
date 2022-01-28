/**
 * Pickup.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
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
    private String id;
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

    /**
     * Create a new Pickup object from a map of parameters.
     *
     * @param params Map of parameters.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public static Pickup create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a new Pickup object from a map of parameters.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public static Pickup create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("pickup", params);

        return request(RequestMethod.POST, classURL(Pickup.class), wrappedParams, Pickup.class, apiKey);
    }

    /**
     * Retrieve a Pickup from the API.
     *
     * @param id ID of Pickup to retrieve.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public static Pickup retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Pickup from the API.
     *
     * @param id     ID of Pickup to retrieve.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public static Pickup retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Pickup.class, id), null, Pickup.class, apiKey);
    }

    /**
     * Get the ID of this Pickup.
     *
     * @return the ID of this Pickup.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of this Pickup.
     *
     * @param id the ID of this Pickup.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the mode of this Pickup.
     *
     * @return the mode of this Pickup.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the mode of this Pickup.
     *
     * @param mode the mode of this Pickup.
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Get the status of this Pickup.
     *
     * @return the status of this Pickup.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status of this Pickup.
     *
     * @param status the status of this Pickup.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Get the reference of this Pickup.
     *
     * @return the reference of this Pickup.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Set the reference of this Pickup.
     *
     * @param reference the reference of this Pickup.
     */
    public void setReference(final String reference) {
        this.reference = reference;
    }

    /**
     * Get the earliest date of this Pickup.
     *
     * @return the earliest date of this Pickup.
     */
    public Date getMinDatetime() {
        return minDatetime;
    }

    /**
     * Set the earliest date of this Pickup.
     *
     * @param minDatetime the earliest date of this Pickup.
     */
    public void setMinDatetime(final Date minDatetime) {
        this.minDatetime = minDatetime;
    }

    /**
     * Get the latest date of this Pickup.
     *
     * @return the latest date of this Pickup.
     */
    public Date getMaxDatetime() {
        return maxDatetime;
    }

    /**
     * Set the latest date of this Pickup.
     *
     * @param maxDatetime the latest date of this Pickup.
     */
    public void setMaxDatetime(final Date maxDatetime) {
        this.maxDatetime = maxDatetime;
    }

    /**
     * Get whether the address is an account address.
     *
     * @return whether the address is an account address.
     */
    public Boolean getIsAccountAddress() {
        return isAccountAddress;
    }

    /**
     * Set whether the address is an account address.
     *
     * @param isAccountAddress if the address is an account address.
     */
    public void setIsAccountAddress(final Boolean isAccountAddress) {
        this.isAccountAddress = isAccountAddress;
    }

    /**
     * Get instructions for the pickup.
     *
     * @return instructions for the pickup.
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Set instructions for the pickup.
     *
     * @param instructions instructions for the pickup.
     */
    public void setInstructions(final String instructions) {
        this.instructions = instructions;
    }

    /**
     * Get the messages of this Pickup.
     *
     * @return List of ShipmentMessages objects.
     */
    public List<ShipmentMessage> getMessages() {
        return messages;
    }

    /**
     * Set the messages of this Pickup.
     *
     * @param messages List of ShipmentMessages objects.
     */
    public void setMessages(final List<ShipmentMessage> messages) {
        this.messages = messages;
    }

    /**
     * Get the confirmation of this Pickup.
     *
     * @return the confirmation of this Pickup.
     */
    public String getConfirmation() {
        return confirmation;
    }

    /**
     * Set the confirmation of this Pickup.
     *
     * @param confirmation the confirmation of this Pickup.
     */
    public void setConfirmation(final String confirmation) {
        this.confirmation = confirmation;
    }

    /**
     * Get the address of this Pickup.
     *
     * @return Address object.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the address of this Pickup.
     *
     * @param address Address object.
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * Get the carrier accounts of this Pickup.
     *
     * @return List of CarrierAccount objects.
     */
    public List<CarrierAccount> getCarrierAccounts() {
        return carrierAccounts;
    }

    /**
     * Set the carrier accounts of this Pickup.
     *
     * @param carrierAccounts List of CarrierAccount objects.
     */
    public void setCarrierAccounts(final List<CarrierAccount> carrierAccounts) {
        this.carrierAccounts = carrierAccounts;
    }

    /**
     * Get the pickup rates of this Pickup.
     *
     * @return List of PickupRate objects.
     */
    public List<PickupRate> getPickupRates() {
        return pickupRates;
    }

    /**
     * Set the pickup rates of this Pickup.
     *
     * @param pickupRates List of PickupRate objects.
     */
    public void setPickupRates(final List<PickupRate> pickupRates) {
        this.pickupRates = pickupRates;
    }

    /**
     * Refresh this Pickup.
     *
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup refresh() throws EasyPostException {
        return this.refresh(null, null);
    }

    /**
     * Refresh this Pickup.
     *
     * @param params Map of parameters.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup refresh(final Map<String, Object> params) throws EasyPostException {
        return this.refresh(params, null);
    }

    /**
     * Refresh this Pickup.
     *
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup refresh(final String apiKey) throws EasyPostException {
        return this.refresh(null, apiKey);
    }

    /**
     * Refresh this Pickup.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup refresh(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, String.format("%s", instanceURL(Pickup.class, this.getId())), params,
                Pickup.class, apiKey);
    }

    /**
     * Buy this Pickup.
     *
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup buy() throws EasyPostException {
        return this.buy(null, null);
    }

    /**
     * Buy this Pickup.
     *
     * @param params Map of parameters.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup buy(final Map<String, Object> params) throws EasyPostException {
        return this.buy(params, null);
    }

    /**
     * Buy this Pickup.
     *
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup buy(final String apiKey) throws EasyPostException {
        return this.buy(null, apiKey);
    }

    /**
     * Buy this Pickup.
     *
     * @param pickupRate PickupRate to buy.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup buy(final PickupRate pickupRate) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rate", pickupRate);

        return this.buy(params, null);
    }

    /**
     * Buy this Pickup.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup buy(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Pickup response =
                request(RequestMethod.POST, String.format("%s/buy", instanceURL(Pickup.class, this.getId())), params,
                        Pickup.class, apiKey);

        this.merge(this, response);
        return this;
    }

    /**
     * Cancel this Pickup.
     *
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup cancel() throws EasyPostException {
        return this.cancel(null, null);
    }

    /**
     * Cancel this Pickup.
     *
     * @param params Map of parameters.
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup cancel(final Map<String, Object> params) throws EasyPostException {
        return this.cancel(params, null);
    }

    /**
     * Cancel this Pickup.
     *
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup cancel(final String apiKey) throws EasyPostException {
        return this.cancel(null, apiKey);
    }

    /**
     * Cancel this Pickup.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Pickup object.
     * @throws EasyPostException when the request fails.
     */
    public Pickup cancel(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.POST, String.format("%s/cancel", instanceURL(Pickup.class, this.getId())), params,
                Pickup.class, apiKey);
    }
}
