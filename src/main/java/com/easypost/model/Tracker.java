package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Tracker extends EasyPostResource {
    private String id;
    private String mode;
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
     * Get the ID of the Tracker.
     *
     * @return the ID of the Tracker.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the Tracker.
     *
     * @param id the ID of the Tracker.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the mode of the Tracker.
     *
     * @return the mode of the Tracker.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the mode of the Tracker.
     *
     * @param mode the mode of the Tracker.
     */
    public void setMode(final String mode) {
        this.mode = mode;
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
     * Get when the tracker was updated.
     *
     * @return when the tracker was updated.
     */
    // This method is a misspelling, but it persists to avoid breaking backwards compatibility
    public Date getUpdateAt() {
        return getUpdatedAt();
    }

    /**
     * Set when the tracker was updated.
     *
     * @param updatedAt when the tracker was updated.
     */
    public void setUpdateAt(final Date updatedAt) {
        setUpdatedAt(updatedAt);
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

    /**
     * Create a new Tracker object using a map of parameters.
     *
     * @param params Map of parameters used to create the Tracker.
     * @return Tracker object.
     * @throws EasyPostException when the request fails.
     */
    public static Tracker create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a new Tracker object using a map of parameters.
     *
     * @param params Map of parameters used to create the Tracker.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Tracker object.
     * @throws EasyPostException when the request fails.
     */
    public static Tracker create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("tracker", params);

        return request(RequestMethod.POST, classURL(Tracker.class), wrappedParams, Tracker.class, apiKey);
    }

    /**
     * Retrieve a Tracker object from the API.
     *
     * @param id ID of the Tracker to retrieve.
     * @return Tracker object.
     * @throws EasyPostException when the request fails.
     */
    public static Tracker retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Tracker object from the API.
     *
     * @param id     ID of the Tracker to retrieve.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Tracker object.
     * @throws EasyPostException when the request fails.
     */
    public static Tracker retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Tracker.class, id), null, Tracker.class, apiKey);
    }

    /**
     * Get a list of all Tracker objects.
     *
     * @param params Map of parameters used to filter the list of Trackers.
     * @return TrackerCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static TrackerCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * Get a list of all Tracker objects.
     *
     * @param params Map of parameters used to filter the list of Trackers.
     * @param apiKey API key to use in request (overrides default API key).
     * @return TrackerCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static TrackerCollection all(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Tracker.class), params, TrackerCollection.class, apiKey);
    }

    /**
     * Create a list of Trackers.
     *
     * @param params Map of parameters used to create the Trackers.
     * @return whether the creation was successful.
     * @throws EasyPostException when the request fails.
     */
    public static boolean createList(final Map<String, Object> params) throws EasyPostException {
        return createList(params, null);
    }

    /**
     * Create a list of Trackers.
     *
     * @param params Map of parameters used to create the Trackers.
     * @param apiKey API key to use in request (overrides default API key).
     * @return whether the creation was successful.
     * @throws EasyPostException when the request fails.
     */
    public static boolean createList(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        String createListUrl = String.format("%s/create_list", classURL(Tracker.class));

        Map<String, Object> newParams = new HashMap<String, Object>();
        newParams.put("trackers", params);

        request(RequestMethod.POST, createListUrl, newParams, Object.class, apiKey);
        // This endpoint does not return a response so we return true here
        return true;
    }
}
