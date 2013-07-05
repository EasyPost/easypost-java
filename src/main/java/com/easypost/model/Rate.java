package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Rate extends EasyPostResource {
	public String id;
	String carrier;
	String service;
	Float rate;
	String shipmentId;
	String serviceCode;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}

	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}

	public String getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}


	public Rate(String id, String carrier, String service, Float rate, String shipmentId) {
		this.id = id;
		this.carrier = carrier;
		this.service = service;
		this.rate = rate;
		this.shipmentId = shipmentId;

		this.serviceCode = carrier.toLowerCase() + "." + service.toLowerCase();
	}
	

	// create
	public static Rate create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static Rate create(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("rate", params);
		
		Rate response;
		response = request(RequestMethod.POST, classURL(Rate.class), wrappedParams, Rate.class, apiKey);

		return response;
	}

	// retrieve
	public static Rate retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}
	public static Rate retrieve(String id, String apiKey) throws EasyPostException {
		Rate response;
		response = request(RequestMethod.GET, instanceURL(Rate.class, id), null, Rate.class, apiKey);

		return response;
	}

	// all
	public static RateCollection all(Map<String, Object> params) throws EasyPostException {
		return all(params, null);
	}
	public static RateCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, classURL(Rate.class), params, RateCollection.class, apiKey);
	}

}
