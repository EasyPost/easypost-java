package com.easypost.model;

import java.util.Date;
import java.util.List;

public final class Tracker extends EasyPostResource {
    private String trackingCode;
    private String status;
    private String shipmentId;
    private String carrier;
    private List<TrackingDetail> trackingDetails;
    private float weight;
    private Date estDeliveryDate;
    private String signedBy;
    private CarrierDetail carrierDetail;
    private String publicUrl;
    private String statusDetail;

    /**
     * Get the carrier of the Tracker.
     *
     * @return the carrier of the Tracker.
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Set the carrier of the Tracker.
     *
     * @param carrier the carrier of the Tracker.
     */
    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    /**
     * Get details about the carrier associated with the Tracker.
     *
     * @return CarrierDetail object.
     */
    public CarrierDetail getCarrierDetail() {
        return carrierDetail;
    }

    /**
     * Set details about the carrier associated with the Tracker.
     *
     * @param carrierDetail details about the carrier associated with the Tracker.
     */
    public void setCarrierDetail(final CarrierDetail carrierDetail) {
        this.carrierDetail = carrierDetail;
    }

    /**
     * Get the estimated delivery date of the Tracker.
     *
     * @return the estimated delivery date of the Tracker.
     */
    public Date getEstDeliveryDate() {
        return estDeliveryDate;
    }

    /**
     * Set the estimated delivery date of the Tracker.
     *
     * @param estDeliveryDate the estimated delivery date of the Tracker.
     */
    public void setEstDeliveryDate(final Date estDeliveryDate) {
        this.estDeliveryDate = estDeliveryDate;
    }

    /**
     * Get the ID of the shipment associated with this tracker.
     *
     * @return the ID of the shipment associated with this tracker.
     */
    public String getShipmentId() {
        return shipmentId;
    }

    /**
     * Set the ID of the shipment associated with this tracker.
     *
     * @param shipmentId the ID of the shipment associated with this tracker.
     */
    public void setShipmentId(final String shipmentId) {
        this.shipmentId = shipmentId;
    }

    /**
     * Get the status of the Tracker.
     *
     * @return the status of the Tracker.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the tracking code of the Tracker.
     *
     * @return the tracking code of the Tracker.
     */
    public String getTrackingCode() {
        return trackingCode;
    }

    /**
     * Set the tracking code of the Tracker.
     *
     * @param trackingCode the tracking code of the Tracker.
     */
    public void setTrackingCode(final String trackingCode) {
        this.trackingCode = trackingCode;
    }

    /**
     * Get the tracking details of the Tracker.
     *
     * @return List of TrackingDetail objects.
     */
    public List<TrackingDetail> getTrackingDetails() {
        return trackingDetails;
    }

    /**
     * Set the tracking details of the Tracker.
     *
     * @param trackingDetails List of TrackingDetail objects.
     */
    public void setTrackingDetails(final List<TrackingDetail> trackingDetails) {
        this.trackingDetails = trackingDetails;
    }

    /**
     * Set the status of the Tracker.
     *
     * @param status the status of the Tracker.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Get the public URL of the Tracker.
     *
     * @return the public URL of the Tracker.
     */
    public String getPublicUrl() {
        return publicUrl;
    }

    /**
     * Set the public URL of the Tracker.
     *
     * @param publicUrl the public URL of the Tracker.
     */
    public void setPublicUrl(final String publicUrl) {
        this.publicUrl = publicUrl;
    }

    /**
     * Get who signed for the package associated Tracker.
     *
     * @return who signed for the package associated Tracker.
     */
    public String getSignedBy() {
        return signedBy;
    }

    /**
     * Set who signed for the package associated Tracker.
     *
     * @param signedBy who signed for the package associated Tracker.
     */
    public void setSignedBy(final String signedBy) {
        this.signedBy = signedBy;
    }

    /**
     * Get the status of the Tracker.
     *
     * @return the status of the Tracker.
     */
    public String getStatusDetail() {
        return statusDetail;
    }

    /**
     * Set the status of the Tracker.
     *
     * @param statusDetail the status of the Tracker.
     */
    public void setStatusDetail(final String statusDetail) {
        this.statusDetail = statusDetail;
    }

    /**
     * Get the weight of the Tracker.
     *
     * @return the weight of the Tracker.
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Set the weight of the Tracker.
     *
     * @param weight the weight of the Tracker.
     */
    public void setWeight(final float weight) {
        this.weight = weight;
    }
}
