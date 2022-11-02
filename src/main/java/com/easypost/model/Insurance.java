package com.easypost.model;

import java.util.List;

public final class Insurance extends EasyPostResource {
    private String reference;
    private Address toAddress;
    private Address fromAddress;
    private Tracker tracker;
    private String provider;
    private String providerId;
    private String trackingCode;
    private String status;
    private String shipmentId;
    private String amount;
    private List<String> messages;

    /**
     * Get the amount of this Insurance.
     *
     * @return the amount of the Insurance
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Set the amount of this Insurance.
     *
     * @param amount the amount of the Insurance
     */
    public void setAmount(final String amount) {
        this.amount = amount;
    }

    /**
     * Get the from address of this Insurance.
     *
     * @return the from address of the Insurance
     */
    public Address getFromAddress() {
        return fromAddress;
    }

    /**
     * Set the from address of this Insurance.
     *
     * @param fromAddress the from address of the Insurance
     */
    public void setFromAddress(final Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * Get the messages of this Insurance.
     *
     * @return list of messages of the Insurance
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     * Set the messages of this Insurance.
     *
     * @param messages list of messages of the Insurance
     */
    public void setMessages(final List<String> messages) {
        this.messages = messages;
    }

    /**
     * Get the provider of this Insurance.
     *
     * @return the provider of the Insurance
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Set the provider of this Insurance.
     *
     * @param provider the provider of the Insurance
     */
    public void setProvider(final String provider) {
        this.provider = provider;
    }

    /**
     * Get the provider ID of this Insurance.
     *
     * @return the provider ID of the Insurance
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * Set the provider ID of this Insurance.
     *
     * @param providerId the provider ID of the Insurance
     */
    public void setProviderId(final String providerId) {
        this.providerId = providerId;
    }

    /**
     * Get the reference of this Insurance.
     *
     * @return the reference of the Insurance
     */
    public String getReference() {
        return reference;
    }

    /**
     * Set the reference of this Insurance.
     *
     * @param reference the reference of the Insurance
     */
    public void setReference(final String reference) {
        this.reference = reference;
    }

    /**
     * Get the to address of this Insurance.
     *
     * @return the to address of the Insurance
     */
    public Address getToAddress() {
        return toAddress;
    }

    /**
     * Set the to address of this Insurance.
     *
     * @param toAddress the to address of the Insurance
     */
    public void setToAddress(final Address toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * Get the tracker of this Insurance.
     *
     * @return Tracker object
     */
    public Tracker getTracker() {
        return tracker;
    }

    /**
     * Set the tracker of this Insurance.
     *
     * @param tracker Tracker object
     */
    public void setTracker(final Tracker tracker) {
        this.tracker = tracker;
    }

    /**
     * Get the shipment ID of this Insurance.
     *
     * @return the shipment ID of the Insurance
     */
    public String getShipmentId() {
        return shipmentId;
    }

    /**
     * Get the status of this Insurance.
     *
     * @return the status of the Insurance
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the tracking code of this Insurance.
     *
     * @return the tracking code of the Insurance
     */
    public String getTrackingCode() {
        return trackingCode;
    }

    /**
     * Set the tracking code of this Insurance.
     *
     * @param trackingCode the tracking code of the Insurance
     */
    public void setTrackingCode(final String trackingCode) {
        this.trackingCode = trackingCode;
    }

    /**
     * Set the status of this Insurance.
     *
     * @param status the status of the Insurance
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Set the shipment ID of this Insurance.
     *
     * @param shipmentId the shipment ID of the Insurance
     */
    public void setShipmentId(final String shipmentId) {
        this.shipmentId = shipmentId;
    }
}
