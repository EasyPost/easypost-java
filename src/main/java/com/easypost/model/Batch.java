package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;
import com.easypost.net.Requestor;
import com.easypost.net.Requestor.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Batch extends EasyPostResource {
    private String state;
    private BatchStatus status;
    private Number numShipments;
    private List<Shipment> shipments;
    private String labelUrl;
    private ScanForm scanForm;
    private String reference;

    /**
     * Get status of this batch.
     *
     * @return status of this batch.
     */
    public BatchStatus getBatchStatus() {
        return status;
    }

    /**
     * Set status of this batch.
     *
     * @param status status of this batch.
     */
    public void setBatchStatus(final BatchStatus status) {
        this.status = status;
    }

    /**
     * Get number of shipments in this batch.
     *
     * @return number of shipments in this batch.
     */
    public Number getNumShipments() {
        return numShipments;
    }

    /**
     * Set number of shipments in this batch.
     *
     * @param numShipments number of shipments in this batch.
     */
    public void setNumShipments(final Number numShipments) {
        this.numShipments = numShipments;
    }

    /**
     * Get reference of this batch.
     *
     * @return reference of this batch.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Set reference of this batch.
     *
     * @param reference reference of this batch.
     */
    public void setReference(final String reference) {
        this.reference = reference;
    }

    /**
     * Get scan form of this batch.
     *
     * @return ScanForm object.
     */
    public ScanForm getScanForm() {
        return scanForm;
    }

    /**
     * Set scan form of this batch.
     *
     * @param scanForm ScanForm object.
     */
    public void setScanForm(final ScanForm scanForm) {
        this.scanForm = scanForm;
    }

    /**
     * Get state of this batch.
     *
     * @return state of this batch.
     */
    public String getState() {
        return state;
    }

    /**
     * Set state of this batch.
     *
     * @param state state of this batch.
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * Create a Batch object.
     *
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public static Batch create() throws EasyPostException {
        return create(null, null);
    }

    /**
     * Create a Batch object.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public static Batch create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("batch", params);

        return Requestor.request(RequestMethod.POST, classURL(Batch.class), wrappedParams, Batch.class, apiKey);
    }

    /**
     * Create a Batch object.
     *
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public static Batch create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Retrieve a Batch object from the API.
     *
     * @param id ID of the Batch to retrieve.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public static Batch retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Batch object from the API.
     *
     * @param id     ID of the Batch to retrieve.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public static Batch retrieve(final String id, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, instanceURL(Batch.class, id), null, Batch.class, apiKey);
    }

    /**
     * List all Batch objects.
     *
     * @param params Map of parameters.
     * @return BatchCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static BatchCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * List all Batch objects.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return BatchCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static BatchCollection all(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, classURL(Batch.class), params, BatchCollection.class, apiKey);
    }

    /**
     * Create and buy a Batch object in one step.
     *
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public static Batch createAndBuy(final Map<String, Object> params) throws EasyPostException {
        return createAndBuy(params, null);
    }

    /**
     * Create and buy a Batch object in one step.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public static Batch createAndBuy(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("batch", params);

        return Requestor.request(RequestMethod.POST, classURL(Batch.class), wrappedParams, Batch.class, apiKey);
    }

    /**
     * Refresh this Batch object.
     *
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch refresh() throws EasyPostException {
        return this.refresh(null, null);
    }

    /**
     * Refresh this Batch object.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch refresh(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, String.format("%s", instanceURL(Batch.class, this.getId())), params,
                Batch.class, apiKey);
    }

    /**
     * Get label URL of this batch.
     *
     * @return Label URL of this batch.x
     */
    public String getLabelUrl() {
        return labelUrl;
    }

    /**
     * Get shipments in this batch.
     *
     * @return list of Shipment objects.
     */
    public List<Shipment> getShipments() {
        return shipments;
    }

    /**
     * Set shipments in this batch.
     *
     * @param shipments list of Shipment objects.
     */
    public void setShipments(final List<Shipment> shipments) {
        this.shipments = shipments;
    }

    /**
     * Set label url of this batch.
     *
     * @param labelUrl Label URL of this batch.
     */
    public void setLabelUrl(final String labelUrl) {
        this.labelUrl = labelUrl;
    }

    /**
     * Refresh this Batch object.
     *
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch refresh(final Map<String, Object> params) throws EasyPostException {
        return this.refresh(params, null);
    }

    /**
     * Refresh this Batch object.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch refresh(final String apiKey) throws EasyPostException {
        return this.refresh(null, apiKey);
    }

    /**
     * Label this Batch object.
     *
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch label() throws EasyPostException {
        return this.label(null, null);
    }

    /**
     * Label this Batch object.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch label(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/label",
            instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
    }

    /**
     * Label this Batch object.
     *
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch label(final Map<String, Object> params) throws EasyPostException {
        return this.label(params, null);
    }

    /**
     * Add shipments to this Batch object.
     *
     * @param shipments List of Shipment objects.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch addShipments(final List<Shipment> shipments) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);
        return this.addShipments(params, null);
    }

    /**
     * Add shipments to this Batch object.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch addShipments(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/add_shipments",
            instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
    }

    /**
     * Add shipments to this Batch object.
     *
     * @param shipments List of Shipment objects.
     * @param apiKey    API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch addShipments(final List<Shipment> shipments, final String apiKey) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);

        return this.addShipments(params, apiKey);
    }

    /**
     * Add shipments to this Batch object.
     *
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch addShipments(final Map<String, Object> params) throws EasyPostException {
        return this.addShipments(params, null);
    }

    /**
     * Remove shipments from this Batch object.
     *
     * @param shipments List of Shipment objects.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch removeShipments(final List<Shipment> shipments) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);

        return this.removeShipments(params, null);
    }

    /**
     * Remove shipments from this Batch object.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch removeShipments(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/remove_shipments",
            instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
    }

    /**
     * Remove shipments from this Batch object.
     *
     * @param shipments List of Shipment objects.
     * @param apiKey    API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch removeShipments(final List<Shipment> shipments, final String apiKey) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);

        return this.removeShipments(params, apiKey);
    }

    /**
     * Remove shipments from this Batch object.
     *
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch removeShipments(final Map<String, Object> params) throws EasyPostException {
        return this.removeShipments(params, null);
    }

    /**
     * Buy this batch.
     *
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch buy() throws EasyPostException {
        return this.buy(null, null);
    }

    /**
     * Buy this batch.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch buy(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/buy",
            instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
    }

    /**
     * Buy this batch.
     *
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch buy(final Map<String, Object> params) throws EasyPostException {
        return this.buy(params, null);
    }

    /**
     * Create a scan form for this batch.
     *
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch createScanForm() throws EasyPostException {
        return this.createScanForm(null, null);
    }

    /**
     * Create a scan form for this batch.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch createScanForm(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/scan_form",
            instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
    }

    /**
     * Create a scan form for this batch.
     *
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch createScanForm(final Map<String, Object> params) throws EasyPostException {
        return this.createScanForm(params, null);
    }
}
