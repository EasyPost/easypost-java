package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Refund extends EasyPostResource {
    private String trackingCode;
    private String confirmationNumber;
    private String status;
    private String carrier;
    private String shipmentId;

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
     * Get refund shipment ID.
     *
     * @return refund shipment ID
     */
    public String getShipmentId() {
        return shipmentId;
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
     * Set refund status.
     *
     * @param status refund status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Set refund shipment ID.
     *
     * @param shipmentId refund shipment ID
     */
    public void setShipmentId(final String shipmentId) {
        this.shipmentId = shipmentId;
    }

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
     * @param apiKey API key to use in request (overrides default API key).
     * @return Refund object
     * @throws EasyPostException when the request fails.
     */
    public static List<Refund> create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("refund", params);

        Refund[] response = Requestor.request(RequestMethod.POST, classURL(Refund.class),
            wrappedParams, Refund[].class, apiKey);

        return Arrays.asList(response);
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
     * @param apiKey API key to use in request (overrides default API key).
     * @return Refund object
     * @throws EasyPostException when the request fails.
     */
    public static Refund retrieve(final String id, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, instanceURL(Refund.class, id), null, Refund.class, apiKey);
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
     * @param apiKey API key to use in request (overrides default API key).
     * @return RefundCollection object
     * @throws EasyPostException when the request fails.
     */
    public static RefundCollection all(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, classURL(Refund.class), params, RefundCollection.class, apiKey);
    }
}
