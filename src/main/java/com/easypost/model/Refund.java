package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Refund extends EasyPostResource {
	public String id;
	String trackingCode;
	String confirmationNumber;
	String status;
	String carrier;
	String shipmentId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTrackingCode() {
		return trackingCode;
	}
	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}
	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}


	// create
	public static List<Refund> create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static List<Refund> create(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("refund", params);
		
		return request(RequestMethod.POST, classURL(Refund.class), wrappedParams, List.class, apiKey);
	}

	// retrieve
	public static Refund retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}
	public static Refund retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(Refund.class, id), null, Refund.class, apiKey);
	}

	// all
	public static RefundCollection all(Map<String, Object> params) throws EasyPostException {
		return all(params, null);
	}
	public static RefundCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, classURL(Refund.class), params, RefundCollection.class, apiKey);
	}

}