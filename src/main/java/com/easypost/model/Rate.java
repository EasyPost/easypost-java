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
	String currency;
	int estDeliveryDays;
  String estDeliveryDate;
  int guaranteedDeliveryDays;
  String guaranteedDeliveryDate;


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

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getEstDeliveryDays() {
		return estDeliveryDays;
	}
	public void setEstDeliveryDays(int estDeliveryDays) {
		this.estDeliveryDays = estDeliveryDays;
	}

	public String getEstDeliveryDate() {
		return estDeliveryDate;
	}
	public void setEstDeliveryDate(String estDeliveryDate) {
		this.estDeliveryDate = estDeliveryDate;
	}

	public int getGuaranteedDeliveryDays() {
		return guaranteedDeliveryDays;
	}
	public void setGuaranteedDeliveryDays(int guaranteedDeliveryDays) {
		this.guaranteedDeliveryDays = guaranteedDeliveryDays;
	}

	public String getGuaranteedDeliveryDate() {
		return guaranteedDeliveryDate;
	}
	public void setGuaranteedDeliveryDate(String guaranteedDeliveryDate) {
		this.guaranteedDeliveryDate = guaranteedDeliveryDate;
	}


	public Rate(String id, String carrier, String service, Float rate, String shipmentId) {
		this.id = id;
		this.carrier = carrier;
		this.service = service;
		this.rate = rate;
		this.shipmentId = shipmentId;
		this.currency = currency;
		this.estDeliveryDays = estDeliveryDays;
  	this.estDeliveryDate = estDeliveryDate;
  	this.guaranteedDeliveryDays = guaranteedDeliveryDays;
  	this.guaranteedDeliveryDate = guaranteedDeliveryDate;
		this.serviceCode = carrier.toLowerCase() + "." + service.toLowerCase();
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

}
