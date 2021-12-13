/**
 * Insurance.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Insurance extends EasyPostResource {
    public String id;
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

    public String getReference() {
        return reference;
    }

    public void setReference(final String reference) {
        this.reference = reference;
    }

    public Address getToAddress() {
        return toAddress;
    }

    public void setToAddress(final Address toAddress) {
        this.toAddress = toAddress;
    }

    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(final Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(final Tracker tracker) {
        this.tracker = tracker;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(final String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(final String providerId) {
        this.providerId = providerId;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(final String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(final String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(final Float amount) {
        this.amount = amount;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(final List<String> messages) {
        this.messages = messages;
    }

    // create
    public static Insurance create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static Insurance create(final Map<String, Object> params,
                                   final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("insurance", params);

        return request(RequestMethod.POST, classURL(Insurance.class),
                wrappedParams, Insurance.class, apiKey);
    }

    // retrieve
    public static Insurance retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static Insurance retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Insurance.class, id),
                null, Insurance.class, apiKey);
    }

    // all
    public static InsuranceCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return all(params, null);
    }

    public static InsuranceCollection all(final Map<String, Object> params,
                                          final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Insurance.class), params,
                InsuranceCollection.class, apiKey);
    }

    // refresh
    public Insurance refresh() throws EasyPostException {
        return this.refresh(null, null);
    }

    public Insurance refresh(final Map<String, Object> params)
            throws EasyPostException {
        return this.refresh(params, null);
    }

    public Insurance refresh(final String apiKey) throws EasyPostException {
        return this.refresh((Map<String, Object>) null, apiKey);
    }

    public Insurance refresh(final Map<String, Object> params,
                             final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET,
                String.format("%s", instanceURL(Insurance.class, this.getId())),
                params, Insurance.class, apiKey);
    }
}
