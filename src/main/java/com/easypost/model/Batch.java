/**
 * Batch.java
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

public final class Batch extends EasyPostResource {
    public String id;
    private String mode;
    private String state;
    public BatchStatus status;
    private Number numShipments;
    private List<Shipment> shipments;
    private String labelUrl;
    private ScanForm scanForm;
    private String reference;

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

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public Number getNumShipments() {
        return numShipments;
    }

    public void setNumShipments(final Number numShipments) {
        this.numShipments = numShipments;
    }

    public List<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(final List<Shipment> shipments) {
        this.shipments = shipments;
    }

    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(final String labelUrl) {
        this.labelUrl = labelUrl;
    }

    public ScanForm getScanForm() {
        return scanForm;
    }

    public void setScanForm(final ScanForm scanForm) {
        this.scanForm = scanForm;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(final String reference) {
        this.reference = reference;
    }


    // create
    public static Batch create() throws EasyPostException {
        return create(null, null);
    }

    public static Batch create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static Batch create(final Map<String, Object> params,
                               final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("batch", params);

        return request(RequestMethod.POST, classURL(Batch.class), wrappedParams,
                Batch.class, apiKey);
    }

    // retrieve
    public static Batch retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static Batch retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Batch.class, id), null,
                Batch.class, apiKey);
    }

    // all
    public static BatchCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return all(params, null);
    }

    public static BatchCollection all(final Map<String, Object> params,
                                      final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Batch.class), params,
                BatchCollection.class, apiKey);
    }

    // create_and_buy
    public static Batch create_and_buy(final Map<String, Object> params)
            throws EasyPostException {
        return create_and_buy(params, null);
    }

    public static Batch create_and_buy(final Map<String, Object> params,
                                       final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("batch", params);

        return request(RequestMethod.POST, classURL(Batch.class), wrappedParams,
                Batch.class, apiKey);
    }

    // refresh
    public Batch refresh() throws EasyPostException {
        return this.refresh(null, null);
    }

    public Batch refresh(final Map<String, Object> params)
            throws EasyPostException {
        return this.refresh(params, null);
    }

    public Batch refresh(final String apiKey) throws EasyPostException {
        return this.refresh((Map<String, Object>) null, apiKey);
    }

    public Batch refresh(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET,
                String.format("%s", instanceURL(Batch.class, this.getId())),
                params, Batch.class, apiKey);
    }

    // label
    public Batch label() throws EasyPostException {
        return this.label(null, null);
    }

    public Batch label(final Map<String, Object> params)
            throws EasyPostException {
        return this.label(params, null);
    }

    public Batch label(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.POST, String.format("%s/label",
                        instanceURL(Batch.class, this.getId())), params, Batch.class,
                apiKey);
    }

    // add_shipments
    public Batch addShipments(final List<Shipment> shipments)
            throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);
        return this.addShipments(params, null);
    }

    public Batch addShipments(final List<Shipment> shipments,
                              final String apiKey) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);
        return this.addShipments(params, apiKey);
    }

    public Batch addShipments(final Map<String, Object> params)
            throws EasyPostException {
        return this.addShipments(params, null);
    }

    public Batch addShipments(final Map<String, Object> params,
                              final String apiKey) throws EasyPostException {
        return request(RequestMethod.POST, String.format("%s/add_shipments",
                        instanceURL(Batch.class, this.getId())), params, Batch.class,
                apiKey);
    }

    // remove_shipments
    public Batch removeShipments(final List<Shipment> shipments)
            throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);
        return this.removeShipments(params, null);
    }

    public Batch removeShipments(final List<Shipment> shipments,
                                 final String apiKey) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);
        return this.removeShipments(params, apiKey);
    }

    public Batch removeShipments(final Map<String, Object> params)
            throws EasyPostException {
        return this.removeShipments(params, null);
    }

    public Batch removeShipments(final Map<String, Object> params,
                                 final String apiKey) throws EasyPostException {
        return request(RequestMethod.POST, String.format("%s/remove_shipments",
                        instanceURL(Batch.class, this.getId())), params, Batch.class,
                apiKey);
    }

    // buy
    public Batch buy() throws EasyPostException {
        return this.buy(null, null);
    }

    public Batch buy(final Map<String, Object> params)
            throws EasyPostException {
        return this.buy(params, null);
    }

    public Batch buy(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.POST,
                String.format("%s/buy", instanceURL(Batch.class, this.getId())),
                params, Batch.class, apiKey);
    }

    // create_scan_form
    public Batch createScanForm() throws EasyPostException {
        return this.createScanForm(null, null);
    }

    public Batch createScanForm(final Map<String, Object> params)
            throws EasyPostException {
        return this.createScanForm(params, null);
    }

    public Batch createScanForm(final Map<String, Object> params,
                                final String apiKey) throws EasyPostException {
        return request(RequestMethod.POST, String.format("%s/scan_form",
                        instanceURL(Batch.class, this.getId())), params, Batch.class,
                apiKey);
    }

}
