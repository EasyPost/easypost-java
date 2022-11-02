package com.easypost.model;

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
}
