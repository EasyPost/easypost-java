package com.easypost.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Batch extends EasyPostResource {
	public String id;
	String mode;
	public BatchStatus status;
	List<Shipment> shipments;
	String labelUrl;


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

	public List<Shipment> getShipments() {
		return shipments;
	}
	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public String getLabelUrl() {
		return labelUrl;
	}
	public void setLabelUrl(String labelUrl) {
		this.labelUrl = labelUrl;
	}


	// create
	public static Batch create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static Batch create(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("batch", params);

		return request(RequestMethod.POST, classURL(Batch.class), wrappedParams, Batch.class, apiKey);
	}

	// retrieve
	public static Batch retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}
	public static Batch retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(Batch.class, id), null, Batch.class, apiKey);
	}

	// all
	public static BatchCollection all(Map<String, Object> params) throws EasyPostException {
		return all(params, null);
	}
	public static BatchCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, classURL(Batch.class), params, BatchCollection.class, apiKey);
	}

	// create_and_buy
	public static Batch create_and_buy(Map<String, Object> params) throws EasyPostException {
		return create_and_buy(params, null);
	}
	public static Batch create_and_buy(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("batch", params);

		return request(RequestMethod.POST, classURL(Batch.class), wrappedParams, Batch.class, apiKey);
	}

	// refresh
	public Batch refresh() throws EasyPostException {
		return this.refresh(null, null);
	}
	public Batch refresh(Map<String, Object> params) throws EasyPostException {
		return this.refresh(params, null);
	}
	public Batch refresh(String apiKey) throws EasyPostException {
		return this.refresh((Map<String, Object>) null, apiKey);
	}
	public Batch refresh(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.GET,
			String.format("%s", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

	// label
	public Batch label() throws EasyPostException {
		return this.label(null, null);
	}
	public Batch label(Map<String, Object> params) throws EasyPostException {
		return this.label(params, null);
	}
	public Batch label(String apiKey) throws EasyPostException {
		return this.label((Map<String, Object>) null, apiKey);
	}
	public Batch label(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.POST,
			String.format("%s/label", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

	// removeShipment
	public Batch removeShipment() throws EasyPostException {
		return this.removeShipment(null, null);
	}
	public Batch removeShipment(Map<String, Object> params) throws EasyPostException {
		return this.removeShipment(params, null);
	}
	public Batch removeShipment(String apiKey) throws EasyPostException {
		return this.removeShipment((Map<String, Object>) null, apiKey);
	}
	public Batch removeShipment(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.POST,
			String.format("%s/remove_shipment", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

	// addShipment
	public Batch addShipment() throws EasyPostException {
		return this.addShipment(null, null);
	}
	public Batch addShipment(Map<String, Object> params) throws EasyPostException {
		return this.addShipment(params, null);
	}
	public Batch addShipment(String apiKey) throws EasyPostException {
		return this.addShipment((Map<String, Object>) null, apiKey);
	}
	public Batch addShipment(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.POST,
			String.format("%s/add_shipment", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

}
