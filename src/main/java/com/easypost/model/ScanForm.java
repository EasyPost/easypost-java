package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class ScanForm extends EasyPostResource {
	public String id;
	String status;
	String message;
	Address fromAddress;
	List<String> trackingCodes;
	String formUrl;
	String formFileType;
	String confirmation;
	String batchId;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
	return status;
	}
	public void setStatus(String status) {
    this.status = status;
  }

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Address getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(Address fromAddress) {
		this.fromAddress = fromAddress;
	}

	public List<String> getTrackingCodes() {
		return trackingCodes;
	}
	public void setTrackingCodes(List<String> trackingCodes) {
		this.trackingCodes = trackingCodes;
	}

	public String getFormUrl() {
		return formUrl;
	}
	public String getLabelUrl() {
		return this.getFormUrl();
	}
	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}

	public String getFormFileType() {
		return formFileType;
	}
	public String getLabelFileType() { return this.getFormFileType(); }
	public void setFormFileType(String formFileType) {
		this.formFileType = formFileType;
	}

	public String getConfirmation() { return confirmation; }
	public void setConfirmation(String confirmation) { this.confirmation = confirmation; }

	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	// create
	public static ScanForm create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static ScanForm create(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.POST, classURL(ScanForm.class), params, ScanForm.class, apiKey);
	}

	// retrieve
	public static ScanForm retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}
	public static ScanForm retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(ScanForm.class, id), null, ScanForm.class, apiKey);
	}

	// all
	public static ScanFormCollection all(Map<String, Object> params) throws EasyPostException {
		return all(params, null);
	}
	public static ScanFormCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, classURL(ScanForm.class), params, ScanFormCollection.class, apiKey);
	}

}
