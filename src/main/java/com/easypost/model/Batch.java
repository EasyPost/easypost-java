package com.easypost.model;

import java.util.List;

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
     * Get label URL of this batch.
     *
     * @return Label URL of this batch.x
     */
    public String getLabelUrl() {
        return labelUrl;
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
}
