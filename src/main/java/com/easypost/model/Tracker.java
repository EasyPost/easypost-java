package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Tracker extends EasyPostResource {
	public String id;
	String mode;
	String trackingCode;
	String status;
	String shipmentId;
	List<TrackingDetail> trackingDetails;
	float weight;
	Date estDeliveryDate;
	String signedBy;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getTrackingCode() {
		return trackingCode;
	}
	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List<TrackingDetail> getTrackingDetails() {
		return trackingDetails;
	}
	public void setTrackingDetails(List<TrackingDetail> trackingDetails) {
		this.trackingDetails = trackingDetails;
	}

	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Date getEstDeliveryDate() {
		return estDeliveryDate;
	}
	public void setEstDeliveryDate(Date estDeliveryDate) {
		this.estDeliveryDate = estDeliveryDate;
	}

	public String getSignedBy() {
		return signedBy;
	}
	public void setSignedBy(String signedBy) {
		this.signedBy = signedBy;
	}

	// create
	public static Tracker create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static Tracker create(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("tracker", params);

		return request(RequestMethod.POST, classURL(Tracker.class), wrappedParams, Tracker.class, apiKey);
	}

	// retrieve
	public static Tracker retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}
	public static Tracker retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(Tracker.class, id), null, Tracker.class, apiKey);
	}

	// all
	public static TrackerCollection all(Map<String, Object> params) throws EasyPostException {
		return all(params, null);
	}
	public static TrackerCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, classURL(Tracker.class), params, TrackerCollection.class, apiKey);
	}

}
