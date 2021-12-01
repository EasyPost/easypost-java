package com.easypost.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Shipment extends EasyPostResource {
	public String id;
	String mode;
	String reference;
	Boolean isReturn;
	Address toAddress;
	Address buyerAddress;
	Address fromAddress;
	Address returnAddress;
	Parcel parcel;
	CustomsInfo customsInfo;
	Rate selectedRate;
	List<Rate> rates;
	PostageLabel postageLabel;
	ScanForm scanForm;
	String orderId;
	List<Form> forms;
	Tracker tracker;
	String insurance;
	String trackingCode;
	String status;
	String refundStatus;
	String batchId;
	String batchStatus;
	String batchMessage;
	String uspsZone;
	Map<String, Object> options;
	List<ShipmentMessage> messages;

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

	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

	public Boolean getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(Boolean isReturn) {
		this.isReturn = isReturn;
	}

	public Address getToAddress() {
		return toAddress;
	}
	public void setToAddress(Address toAddress) {
		this.toAddress = toAddress;
	}

	public Address getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(Address buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public Address getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(Address fromAddress) {
		this.fromAddress = fromAddress;
	}

	public Address getReturnAddress() {
		return returnAddress;
	}
	public void setReturnAddress(Address returnAddress) {
		this.returnAddress = returnAddress;
	}

	public Parcel getParcel() {
		return parcel;
	}
	public void setParcel(Parcel parcel) {
		this.parcel = parcel;
	}

	public CustomsInfo getCustomsInfo() {
		return customsInfo;
	}
	public void setCustomsInfo(CustomsInfo customsInfo) {
		this.customsInfo = customsInfo;
	}

	public Rate getSelectedRate() {
		return selectedRate;
	}
	public void setSelectedRate(Rate selectedRate) {
		this.selectedRate = selectedRate;
	}

	public List<Rate> getRates() {
		return rates;
	}
	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

	public PostageLabel getPostageLabel() {
		return postageLabel;
	}
	public void setPostageLabel(PostageLabel postageLabel) {
		this.postageLabel = postageLabel;
	}

	public ScanForm getScanForm() {
		return scanForm;
	}
	public void setScanForm(ScanForm scanForm) {
		this.scanForm = scanForm;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Tracker getTracker() {
		return tracker;
	}
	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}

	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
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

	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchStatus() {
		return batchStatus;
	}
	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}

	public String getBatchMessage() {
		return batchMessage;
	}
	public void setBatchMessage(String batchMessage) {
		this.batchMessage = batchMessage;
	}

	public String getUspsZone() {
		return uspsZone;
	}
	public void setUspsZone(String uspsZone) {
		this.uspsZone = uspsZone;
	}

	public Map<String, Object> getOptions() {
		return options;
	}
	public void setOptions(Map<String, Object> options) {
		this.options = options;
	}

	public List<ShipmentMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<ShipmentMessage> messages) {
		this.messages = messages;
	}

	public List<Form> getForms() {
		return forms;
	}
	public void setForms(List<Form> forms) {
		this.forms = forms;
	}


	// create
	public static Shipment create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static Shipment create(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("shipment", params);

		return request(RequestMethod.POST, classURL(Shipment.class), wrappedParams, Shipment.class, apiKey);
	}

	// retrieve
	public static Shipment retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}
	public static Shipment retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(Shipment.class, id), null, Shipment.class, apiKey);
	}

	// all
	public static ShipmentCollection all(Map<String, Object> params) throws EasyPostException {
		return all(params, null);
	}
	public static ShipmentCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, classURL(Shipment.class), params, ShipmentCollection.class, apiKey);
	}

	// refresh
	public Shipment refresh() throws EasyPostException {
		return this.refresh(null, null);
	}
	public Shipment refresh(Map<String, Object> params) throws EasyPostException {
		return this.refresh(params, null);
	}
	public Shipment refresh(String apiKey) throws EasyPostException {
		return this.refresh((Map<String, Object>) null, apiKey);
	}
	public Shipment refresh(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.GET,
			String.format("%s", instanceURL(Shipment.class, this.getId())), params, Shipment.class, apiKey);
	}

	// get rates
	public Shipment newRates() throws EasyPostException {
		return this.newRates(null, null);
	}
	public Shipment newRates(Map<String, Object> params) throws EasyPostException {
		return this.newRates(params, null);
	}
	public Shipment newRates(String apiKey) throws EasyPostException {
		return this.newRates((Map<String, Object>) null, apiKey);
	}
	public Shipment newRates(Map<String, Object> params, String apiKey) throws EasyPostException {
		Shipment response = request(
			RequestMethod.GET,
			String.format("%s/rates", instanceURL(Shipment.class, this.getId())), params, Shipment.class, apiKey);

		this.merge(this, response);
		return this;
	}

	// get smartrates
    public List<Rate> getSmartrates() throws EasyPostException {
        return this.getSmartrates(null, null);
    }
    public List<Rate> getSmartrates(Map<String, Object> params) throws EasyPostException {
        return this.getSmartrates(params, null);
    }
    public List<Rate> getSmartrates(String apiKey) throws EasyPostException {
        return this.getSmartrates((Map<String, Object>) null, apiKey);
    }
    public List<Rate> getSmartrates(Map<String, Object> params, String apiKey) throws EasyPostException {
        SmartrateCollection smartrateCollection = (SmartrateCollection) request(
            RequestMethod.GET,
            String.format("%s/smartrate", instanceURL(Shipment.class, this.getId())),
            params,
            SmartrateCollection.class,
            apiKey
        );

        return smartrateCollection.getRates();
    }

	// buy
	public Shipment buy() throws EasyPostException {
		return this.buy(null, null);
	}
	public Shipment buy(Map<String, Object> params) throws EasyPostException {
		return this.buy(params, null);
	}
	public Shipment buy(String apiKey) throws EasyPostException {
		return this.buy((Map<String, Object>) null, apiKey);
	}
	public Shipment buy(Rate rate) throws EasyPostException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rate", rate);

		return this.buy(params, null);
	}
	public Shipment buy(Map<String, Object> params, String apiKey) throws EasyPostException {
		Shipment response = request(
			RequestMethod.POST,
			String.format("%s/buy", instanceURL(Shipment.class, this.getId())), params, Shipment.class, apiKey);

		this.merge(this, response);
		return this;
	}

	// refund
	public Shipment refund() throws EasyPostException {
		return this.refund(null, null);
	}
	public Shipment refund(Map<String, Object> params) throws EasyPostException {
		return this.refund(params, null);
	}
	public Shipment refund(String apiKey) throws EasyPostException {
		return this.refund((Map<String, Object>) null, apiKey);
	}
	public Shipment refund(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.GET,
			String.format("%s/refund", instanceURL(Shipment.class, this.getId())), params, Shipment.class, apiKey);
	}

	// label
	public Shipment label() throws EasyPostException {
		return this.label(null, null);
	}
	public Shipment label(Map<String, Object> params) throws EasyPostException {
		return this.label(params, null);
	}
	public Shipment label(String apiKey) throws EasyPostException {
		return this.label((Map<String, Object>) null, apiKey);
	}
	public Shipment label(Map<String, Object> params, String apiKey) throws EasyPostException {
		Shipment response = request(
			RequestMethod.GET,
			String.format("%s/label", instanceURL(Shipment.class, this.getId())), params, Shipment.class, apiKey);

		this.merge(this, response);
		return this;
	}

	// insure
	public Shipment insure() throws EasyPostException {
		return this.insure(null, null);
	}
	public Shipment insure(Map<String, Object> params) throws EasyPostException {
		return this.insure(params, null);
	}
	public Shipment insure(String apiKey) throws EasyPostException {
		return this.insure((Map<String, Object>) null, apiKey);
	}
	public Shipment insure(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(
			RequestMethod.POST,
			String.format("%s/insure", instanceURL(Shipment.class, this.getId())), params, Shipment.class, apiKey);
	}

	// lowest rate
	public Rate lowestRate() throws EasyPostException {
		return this.lowestRate(null, null);
	}
	public Rate lowestRate(List<String> carriers) throws EasyPostException {
		return this.lowestRate(carriers, null);
	}
	public Rate lowestRate(List<String> carriers, List<String> services) throws EasyPostException {
		Rate lowestRate = null;

		if (carriers != null) {
			for(int i=0; i < carriers.size(); i++) {
				carriers.set(i, carriers.get(i).toLowerCase());
			}
		}

		if (services != null) {
			for(int i=0; i < services.size(); i++) {
				services.set(i, services.get(i).toLowerCase());
			}
		}

		for(int i=0; i < this.rates.size(); i++) {
			if (carriers != null && carriers.size() > 0 && !carriers.contains(this.rates.get(i).carrier.toLowerCase()) && !carriers.contains(this.rates.get(i).serviceCode.toLowerCase())) {
				continue;
			}
			if (services != null && services.size() > 0 && !services.contains(this.rates.get(i).service.toLowerCase()) && !services.contains(this.rates.get(i).serviceCode.toLowerCase())) {
				continue;
			}

			if (lowestRate == null || lowestRate.rate > this.rates.get(i).rate) {
				lowestRate = this.rates.get(i);
			}
		}

		if(lowestRate == null) {
			throw new EasyPostException("Unable to find lowest rate matching required criteria.");
		}

		return lowestRate;
	}
}
