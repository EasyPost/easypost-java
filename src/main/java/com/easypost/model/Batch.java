package com.easypost.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Batch extends EasyPostResource {
	public String id;
	String mode;
	String state;
	public BatchStatus status;
  Number numShipments;
	List<Shipment> shipments;
	String labelUrl;
	ScanForm scanForm;
  String reference;

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

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

  public Number getNumShipments() {
    return numShipments;
  }
  public void setNumShipments(Number numShipments) {
    this.numShipments = numShipments;
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

	public ScanForm getScanForm() {
		return scanForm;
	}
	public void setScanForm(ScanForm scanForm) {
		this.scanForm = scanForm;
	}

  public String getReference() {
    return reference;
  }
  public void setReference(String reference) {
    this.reference = reference;
  }


	// create
	public static Batch create() throws EasyPostException {
		return create(null, null);
	}
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
	public Batch label(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.POST,
			String.format("%s/label", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

	// add_shipments
	public Batch addShipments(List<Shipment> shipments) throws EasyPostException {
		Map<String, Object> params = new HashMap<String, Object>();
   	params.put("shipments", shipments);
		return this.addShipments(params, null);
	}
	public Batch addShipments(List<Shipment> shipments, String apiKey) throws EasyPostException {
		Map<String, Object> params = new HashMap<String, Object>();
   	params.put("shipments", shipments);
		return this.addShipments(params, apiKey);
	}
	public Batch addShipments(Map<String, Object> params) throws EasyPostException {
		return this.addShipments(params, null);
	}
	public Batch addShipments(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.POST,
			String.format("%s/add_shipments", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

	// remove_shipments
	public Batch removeShipments(List<Shipment> shipments) throws EasyPostException {
		Map<String, Object> params = new HashMap<String, Object>();
   	params.put("shipments", shipments);
		return this.removeShipments(params, null);
	}
	public Batch removeShipments(List<Shipment> shipments, String apiKey) throws EasyPostException {
		Map<String, Object> params = new HashMap<String, Object>();
   	params.put("shipments", shipments);
		return this.removeShipments(params, apiKey);
	}
	public Batch removeShipments(Map<String, Object> params) throws EasyPostException {
		return this.removeShipments(params, null);
	}
	public Batch removeShipments(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.POST,
			String.format("%s/remove_shipments", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

  // buy
  public Batch buy() throws EasyPostException {
    return this.buy(null, null);
  }
  public Batch buy(Map<String, Object> params) throws EasyPostException {
    return this.buy(params, null);
  }
  public Batch buy(Map<String, Object> params, String apiKey) throws EasyPostException {
    return request(
      RequestMethod.POST,
      String.format("%s/buy", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
  }

	// create_scan_form
	public Batch createScanForm() throws EasyPostException {
		return this.createScanForm(null, null);
	}
	public Batch createScanForm(Map<String, Object> params) throws EasyPostException {
		return this.createScanForm(params, null);
	}
	public Batch createScanForm(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.POST,
			String.format("%s/scan_form", instanceURL(Batch.class, this.getId())), params, Batch.class, apiKey);
	}

}
