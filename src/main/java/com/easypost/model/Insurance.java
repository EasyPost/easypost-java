package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Insurance extends EasyPostResource {
    private String id;
    private String mode;
    private String reference;
    private Address toAddress;
    private Address fromAddress;
    private Tracker tracker;
    private String provider;
    private String providerId;
    private String trackingCode;
    private String status;
    private String shipmentId;
    private Float amount;
    private List<String> messages;

    /**
     * Create a new Insurance object from a map of parameters.
     *
     * @param params map of parameters
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public static Insurance create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a new Insurance object from a map of parameters.
     *
     * @param params a map of parameters
     * @param apiKey API key to use in request (overrides default API key).
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public static Insurance create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("insurance", params);

        return request(RequestMethod.POST, classURL(Insurance.class), wrappedParams, Insurance.class, apiKey);
    }

    /**
     * Retrieve an Insurance from the API.
     *
     * @param id the id of the Insurance to retrieve
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public static Insurance retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve an Insurance from the API.
     *
     * @param id     the id of the Insurance to retrieve
     * @param apiKey API key to use in request (overrides default API key).
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public static Insurance retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Insurance.class, id), null, Insurance.class, apiKey);
    }

    /**
     * Get a list of Insurances.
     *
     * @param params a map of parameters
     * @return InsuranceCollection object
     * @throws EasyPostException when the request fails.
     */
    public static InsuranceCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * Get a list of Insurances.
     *
     * @param params a map of parameters
     * @param apiKey API key to use in request (overrides default API key).
     * @return InsuranceCollection object
     * @throws EasyPostException when the request fails.
     */
    public static InsuranceCollection all(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Insurance.class), params, InsuranceCollection.class, apiKey);
    }

    /**
     * Get the ID of this Insurance.
     *
     * @return the ID of the Insurance
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of this Insurance.
     *
     * @param id the ID of the Insurance
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the mode of this Insurance.
     *
     * @return the mode of the Insurance
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the mode of this Insurance.
     *
     * @param mode the mode of the Insurance
     */
    public void setMode(final String mode) {
        this.mode = mode;
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
     * Get the status of this Insurance.
     *
     * @return the status of the Insurance
     */
    public String getStatus() {
        return status;
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
     * Get the shipment ID of this Insurance.
     *
     * @return the shipment ID of the Insurance
     */
    public String getShipmentId() {
        return shipmentId;
    }

    /**
     * Set the shipment ID of this Insurance.
     *
     * @param shipmentId the shipment ID of the Insurance
     */
    public void setShipmentId(final String shipmentId) {
        this.shipmentId = shipmentId;
    }

    /**
     * Get the amount of this Insurance.
     *
     * @return the amount of the Insurance
     */
    public Float getAmount() {
        return amount;
    }

    /**
     * Set the amount of this Insurance.
     *
     * @param amount the amount of the Insurance
     */
    public void setAmount(final Float amount) {
        this.amount = amount;
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
     * Refresh this Insurance.
     *
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public Insurance refresh() throws EasyPostException {
        return this.refresh(null, null);
    }

    /**
     * Refresh this Insurance.
     *
     * @param params a map of parameters
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public Insurance refresh(final Map<String, Object> params) throws EasyPostException {
        return this.refresh(params, null);
    }

    /**
     * Refresh this Insurance.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public Insurance refresh(final String apiKey) throws EasyPostException {
        return this.refresh(null, apiKey);
    }

    /**
     * Refresh this Insurance.
     *
     * @param params a map of parameters
     * @param apiKey API key to use in request (overrides default API key).
     * @return Insurance object
     * @throws EasyPostException when the request fails.
     */
    public Insurance refresh(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, String.format("%s", instanceURL(Insurance.class, this.getId())), params,
                Insurance.class, apiKey);
    }
}
