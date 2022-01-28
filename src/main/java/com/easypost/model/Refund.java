/**
 * Refund.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Refund extends EasyPostResource {
    private String id;
    private String trackingCode;
    private String confirmationNumber;
    private String status;
    private String carrier;
    private String shipmentId;

    /**
     * Create a Refund object from a map of parameters.
     *
     * @param params Map of parameters
     * @return Refund object
     * @throws EasyPostException when the request fails.
     */
    public static List<Refund> create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a Refund object from a map of parameters.
     *
     * @param params Map of parameters
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Refund object
     * @throws EasyPostException when the request fails.
     */
    public static List<Refund> create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("refund", params);

        return request(RequestMethod.POST, classURL(Refund.class), wrappedParams, List.class, apiKey);
    }

    /**
     * Retrieve a Refund object from the API.
     *
     * @param id ID of refund to retrieve
     * @return Refund object
     * @throws EasyPostException when the request fails.
     */
    public static Refund retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Refund object from the API.
     *
     * @param id     ID of refund to retrieve
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Refund object
     * @throws EasyPostException when the request fails.
     */
    public static Refund retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Refund.class, id), null, Refund.class, apiKey);
    }

    /**
     * List all Refunds objects.
     *
     * @param params Map of parameters
     * @return RefundCollection object
     * @throws EasyPostException when the request fails.
     */
    public static RefundCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * List all Refunds objects.
     *
     * @param params Map of parameters
     * @param apiKey API key to use in request (ovverides default API key).
     * @return RefundCollection object
     * @throws EasyPostException when the request fails.
     */
    public static RefundCollection all(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, classURL(Refund.class), params, RefundCollection.class, apiKey);
    }

    /**
     * Get refund ID.
     *
     * @return refund ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set refund ID.
     *
     * @param id refund ID
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get refund tracking code.
     *
     * @return refund tracking code
     */
    public String getTrackingCode() {
        return trackingCode;
    }

    /**
     * Set refund tracking code.
     *
     * @param trackingCode refund tracking code
     */
    public void setTrackingCode(final String trackingCode) {
        this.trackingCode = trackingCode;
    }

    /**
     * Get refund confirmation number.
     *
     * @return refund confirmation number
     */
    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * Set refund confirmation number.
     *
     * @param confirmationNumber refund confirmation number
     */
    public void setConfirmationNumber(final String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    /**
     * Get refund status.
     *
     * @return refund status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set refund status.
     *
     * @param status refund status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Get refund carrier.
     *
     * @return refund carrier
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Set refund carrier.
     *
     * @param carrier refund carrier
     */
    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    /**
     * Get refund shipment ID.
     *
     * @return refund shipment ID
     */
    public String getShipmentId() {
        return shipmentId;
    }

    /**
     * Set refund shipment ID.
     *
     * @param shipmentId refund shipment ID
     */
    public void setShipmentId(final String shipmentId) {
        this.shipmentId = shipmentId;
    }
}
