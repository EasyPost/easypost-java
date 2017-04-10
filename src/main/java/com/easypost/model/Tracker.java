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
	String carrier;
	List<TrackingDetail> trackingDetails;
	float weight;
	Date estDeliveryDate;
	String signedBy;
	CarrierDetail carrierDetail;
	Date createdAt;
	Date updatedAt;
	String publicUrl;
	String statusDetail;

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

	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
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

	public CarrierDetail getCarrierDetail() { return carrierDetail; }
	public void setCarrierDetail(CarrierDetail carrierDetail) { this.carrierDetail = carrierDetail; }

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateAt() {
		return updatedAt;
	}
	public void setUpdateAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getPublicUrl() { return publicUrl; }
	public void setPublicUrl(String publicUrl) { this.publicUrl = publicUrl; }

	public String getStatusDetail() {
		return statusDetail;
	}
	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
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

	// createList
	public static boolean createList(Map<String, Object> params) throws EasyPostException {
		return createList(params, null);
	}
	public static boolean createList(Map<String, Object> params, String apiKey) throws EasyPostException {
		String createListUrl = String.format("%s/create_list", classURL(Tracker.class));

		request(RequestMethod.POST, createListUrl, params, Object.class, apiKey);
		return true;
	}

}
