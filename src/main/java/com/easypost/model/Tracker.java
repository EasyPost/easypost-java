/**
 * Tracker.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Tracker extends EasyPostResource {
    public String id;
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

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(final String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(final String carrier) {
        this.carrier = carrier;
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

    public List<TrackingDetail> getTrackingDetails() {
        return trackingDetails;
    }

    public void setTrackingDetails(final List<TrackingDetail> trackingDetails) {
        this.trackingDetails = trackingDetails;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(final float weight) {
        this.weight = weight;
    }

    public Date getEstDeliveryDate() {
        return estDeliveryDate;
    }

    public void setEstDeliveryDate(final Date estDeliveryDate) {
        this.estDeliveryDate = estDeliveryDate;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(final String signedBy) {
        this.signedBy = signedBy;
    }

    public CarrierDetail getCarrierDetail() {
        return carrierDetail;
    }

    public void setCarrierDetail(final CarrierDetail carrierDetail) {
        this.carrierDetail = carrierDetail;
    }

    // This method is a misspelling, but it persists to avoid breaking backwards compatibility
    public Date getUpdateAt() {
        return getUpdatedAt();
    }

    public void setUpdateAt(final Date updatedAt) {
        setUpdatedAt(updatedAt);
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(final String publicUrl) {
        this.publicUrl = publicUrl;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(final String statusDetail) {
        this.statusDetail = statusDetail;
    }

    // create
    public static Tracker create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static Tracker create(final Map<String, Object> params,
                                 final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("tracker", params);

        return request(RequestMethod.POST, classURL(Tracker.class),
                wrappedParams, Tracker.class, apiKey);
    }

    // retrieve
    public static Tracker retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static Tracker retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Tracker.class, id), null,
                Tracker.class, apiKey);
    }

    // all
    public static TrackerCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return all(params, null);
    }

    public static TrackerCollection all(final Map<String, Object> params,
                                        final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Tracker.class), params,
                TrackerCollection.class, apiKey);
    }

    // createList
    public static boolean createList(final Map<String, Object> params)
            throws EasyPostException {
        return createList(params, null);
    }

    public static boolean createList(final Map<String, Object> params,
                                     final String apiKey)
            throws EasyPostException {
        String createListUrl =
                String.format("%s/create_list", classURL(Tracker.class));

        int count = 0;
        Map<String, Object> newParams = new HashMap<String, Object>();
        Map<String, Object> trackers = new HashMap<String, Object>();
        for (Object tracker : (ArrayList) params.get("trackers")) {
            trackers.put(String.valueOf(count), tracker);
            count++;
        }
        newParams.put("trackers", trackers);

        request(RequestMethod.POST, createListUrl, newParams, Object.class,
                apiKey);
        return true;
    }

}
