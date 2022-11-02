package com.easypost.model;

import java.util.Date;
import java.util.List;

public final class Pickup extends EasyPostResource {
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
     * Get the pickupRates of this Pickup.
     *
     * @return the pickupRates of this Pickup.
     */
    public List<PickupRate> getPickoutRates() {
        return pickupRates;
    }

    /**
     * Set the pickupRates of this Pickup.
     *
     * @param pickupRates the pickupRates of this Pickup.
     */
    public void setPickupRates(final List<PickupRate> pickupRates) {
        this.pickupRates = pickupRates;
    }
}
