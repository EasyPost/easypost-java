package com.easypost.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Insurance extends EasyPostResource {
	public String id;
	String mode;
	String reference;
	Address toAddress;
	Address fromAddress;
	Tracker tracker;
	String provider;
	String providerId;
	String trackingCode;
	String status;
	String shipmentId;
	Float amount;
	List<String> messages;

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public String getMode() { return mode; }
	public void setMode(String mode) { this.mode = mode; }

	public String getReference() { return reference; }
	public void setReference(String reference) { this.reference = reference; }

	public Address getToAddress() { return toAddress; }
	public void setToAddress(Address toAddress) { this.toAddress = toAddress; }

	public Address getFromAddress() { return fromAddress; }
	public void setFromAddress(Address fromAddress) { this.fromAddress = fromAddress; }

	public Tracker getTracker() { return tracker; }
	public void setTracker(Tracker tracker) { this.tracker = tracker; }

	public String getProvider() { return provider; }
	public void setProvider(String provider) { this.provider = provider; }

	public String getProviderId() { return providerId; }
	public void setProviderId(String providerId) { this.providerId = providerId; }

	public String getTrackingCode() { return trackingCode; }
	public void setTrackingCode(String trackingCode) { this.trackingCode = trackingCode; }

	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }

	public String getShipmentId() { return shipmentId; }
	public void setShipmentId(String shipmentId) { this.shipmentId = shipmentId; }

	public Float getAmount() { return amount; }
	public void setAmount(Float amount) { this.amount = amount; }

	public List<String> getMessages() { return messages; }
	public void setMessages(List<String> messages) { this.messages = messages; }

	// create
	public static Insurance create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static Insurance create(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("insurance", params);

		return request(RequestMethod.POST, classURL(Insurance.class), wrappedParams, Insurance.class, apiKey);
	}

	// retrieve
	public static Insurance retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}
	public static Insurance retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(Insurance.class, id), null, Insurance.class, apiKey);
	}

	// all
	public static InsuranceCollection all(Map<String, Object> params) throws EasyPostException {
		return all(params, null);
	}
	public static InsuranceCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, classURL(Insurance.class), params, InsuranceCollection.class, apiKey);
	}

	// refresh
	public Insurance refresh() throws EasyPostException {
		return this.refresh(null, null);
	}
	public Insurance refresh(Map<String, Object> params) throws EasyPostException {
		return this.refresh(params, null);
	}
	public Insurance refresh(String apiKey) throws EasyPostException {
		return this.refresh((Map<String, Object>) null, apiKey);
	}
	public Insurance refresh(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.GET,
			String.format("%s", instanceURL(Insurance.class, this.getId())), params, Insurance.class, apiKey);
	}
}
