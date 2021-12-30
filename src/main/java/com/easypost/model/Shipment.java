/**
 * Shipment.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Shipment extends EasyPostResource {
    public String id;
    private String mode;
    private String reference;
    private Boolean isReturn;
    private Address toAddress;
    private Address buyerAddress;
    private Address fromAddress;
    private Address returnAddress;
    private Parcel parcel;
    private CustomsInfo customsInfo;
    private Rate selectedRate;
    private List<Rate> rates;
    private PostageLabel postageLabel;
    private ScanForm scanForm;
    private String orderId;
    private List<Form> forms;
    private Tracker tracker;
    private String insurance;
    private String trackingCode;
    private String status;
    private String refundStatus;
    private String batchId;
    private String batchStatus;
    private String batchMessage;
    private String uspsZone;
    private Map<String, Object> options;
    private List<ShipmentMessage> messages;
    private List<TaxIdentifier> taxIdentifiers;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(final String mode) {
        this.mode = mode;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(final String reference) {
        this.reference = reference;
    }

    public Boolean getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(final Boolean isReturn) {
        this.isReturn = isReturn;
    }

    public Address getToAddress() {
        return toAddress;
    }

    public void setToAddress(final Address toAddress) {
        this.toAddress = toAddress;
    }

    public Address getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(final Address buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(final Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    public Address getReturnAddress() {
        return returnAddress;
    }

    public void setReturnAddress(final Address returnAddress) {
        this.returnAddress = returnAddress;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(final Parcel parcel) {
        this.parcel = parcel;
    }

    public CustomsInfo getCustomsInfo() {
        return customsInfo;
    }

    public void setCustomsInfo(final CustomsInfo customsInfo) {
        this.customsInfo = customsInfo;
    }

    public Rate getSelectedRate() {
        return selectedRate;
    }

    public void setSelectedRate(final Rate selectedRate) {
        this.selectedRate = selectedRate;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(final List<Rate> rates) {
        this.rates = rates;
    }

    public PostageLabel getPostageLabel() {
        return postageLabel;
    }

    public void setPostageLabel(final PostageLabel postageLabel) {
        this.postageLabel = postageLabel;
    }

    public ScanForm getScanForm() {
        return scanForm;
    }

    public void setScanForm(final ScanForm scanForm) {
        this.scanForm = scanForm;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(final Tracker tracker) {
        this.tracker = tracker;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(final String insurance) {
        this.insurance = insurance;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(final String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(final String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(final String batchId) {
        this.batchId = batchId;
    }

    public String getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(final String batchStatus) {
        this.batchStatus = batchStatus;
    }

    public String getBatchMessage() {
        return batchMessage;
    }

    public void setBatchMessage(final String batchMessage) {
        this.batchMessage = batchMessage;
    }

    public String getUspsZone() {
        return uspsZone;
    }

    public void setUspsZone(final String uspsZone) {
        this.uspsZone = uspsZone;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(final Map<String, Object> options) {
        this.options = options;
    }

    public List<ShipmentMessage> getMessages() {
        return messages;
    }

    public void setMessages(final List<ShipmentMessage> messages) {
        this.messages = messages;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(final List<Form> forms) {
        this.forms = forms;
    }

    public List<TaxIdentifier> getTaxIdentifiers() {
        return taxIdentifiers;
    }

    public void setTaxIdentifiers(final List<TaxIdentifier> taxIdentifiers) {
        this.taxIdentifiers = taxIdentifiers;
    }


    // create
    public static Shipment create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static Shipment create(final Map<String, Object> params,
                                  final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("shipment", params);

        return request(RequestMethod.POST, classURL(Shipment.class),
                wrappedParams, Shipment.class, apiKey);
    }

    // retrieve
    public static Shipment retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static Shipment retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Shipment.class, id), null,
                Shipment.class, apiKey);
    }

    // all
    public static ShipmentCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return all(params, null);
    }

    public static ShipmentCollection all(final Map<String, Object> params,
                                         final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Shipment.class), params,
                ShipmentCollection.class, apiKey);
    }

    // refresh
    public Shipment refresh() throws EasyPostException {
        return this.refresh(null, null);
    }

    public Shipment refresh(final Map<String, Object> params)
            throws EasyPostException {
        return this.refresh(params, null);
    }

    public Shipment refresh(final String apiKey) throws EasyPostException {
        return this.refresh((Map<String, Object>) null, apiKey);
    }

    public Shipment refresh(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET,
                String.format("%s", instanceURL(Shipment.class, this.getId())),
                params, Shipment.class, apiKey);
    }

    // get rates
    public Shipment newRates() throws EasyPostException {
        return this.newRates(null, null);
    }

    public Shipment newRates(final Map<String, Object> params)
            throws EasyPostException {
        return this.newRates(params, null);
    }

    public Shipment newRates(final String apiKey) throws EasyPostException {
        return this.newRates((Map<String, Object>) null, apiKey);
    }

    public Shipment newRates(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        Shipment response = request(RequestMethod.POST,
                String.format("%s/rerate",
                        instanceURL(Shipment.class, this.getId())), params,
                Shipment.class, apiKey);
        this.merge(this, response);
        return this;
    }

    // get smartrates
    public List<Rate> getSmartrates() throws EasyPostException {
        return this.getSmartrates(null, null);
    }

    public List<Rate> getSmartrates(final Map<String, Object> params)
            throws EasyPostException {
        return this.getSmartrates(params, null);
    }

    public List<Rate> getSmartrates(final String apiKey) throws EasyPostException {
        return this.getSmartrates((Map<String, Object>) null, apiKey);
    }

    public List<Rate> getSmartrates(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        SmartrateCollection smartrateCollection =
                (SmartrateCollection) request(RequestMethod.GET,
                        String.format("%s/smartrate",
                                instanceURL(Shipment.class, this.getId())),
                        params, SmartrateCollection.class, apiKey);
        return smartrateCollection.getRates();
    }

    // buy
    public Shipment buy() throws EasyPostException {
        return this.buy(null, null);
    }

    public Shipment buy(final Map<String, Object> params) throws EasyPostException {
        return this.buy(params, null);
    }

    public Shipment buy(final String apiKey) throws EasyPostException {
        return this.buy((Map<String, Object>) null, apiKey);
    }

    public Shipment buy(final Rate rate) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rate", rate);

        return this.buy(params, null);
    }
  
    public Shipment buy(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        Shipment response = request(RequestMethod.POST, String.format("%s/buy",
                        instanceURL(Shipment.class, this.getId())), params,
                Shipment.class, apiKey);
        this.merge(this, response);
        return this;
    }

    // refund
    public Shipment refund() throws EasyPostException {
        return this.refund(null, null);
    }

    public Shipment refund(final Map<String, Object> params)
            throws EasyPostException {
        return this.refund(params, null);
    }

    public Shipment refund(final String apiKey) throws EasyPostException {
        return this.refund((Map<String, Object>) null, apiKey);
    }

    public Shipment refund(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, String.format("%s/refund",
                        instanceURL(Shipment.class, this.getId())), params,
                Shipment.class, apiKey);
    }

    // label
    public Shipment label() throws EasyPostException {
        return this.label(null, null);
    }

    public Shipment label(final Map<String, Object> params) throws EasyPostException {
        return this.label(params, null);
    }

    public Shipment label(final String apiKey) throws EasyPostException {
        return this.label((Map<String, Object>) null, apiKey);
    }

    public Shipment label(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        Shipment response = request(RequestMethod.GET, String.format("%s/label",
                        instanceURL(Shipment.class, this.getId())), params,
                Shipment.class, apiKey);
        this.merge(this, response);
        return this;
    }

    // insure
    public Shipment insure() throws EasyPostException {
        return this.insure(null, null);
    }

    public Shipment insure(final Map<String, Object> params)
            throws EasyPostException {
        return this.insure(params, null);
    }

    public Shipment insure(final String apiKey) throws EasyPostException {
        return this.insure((Map<String, Object>) null, apiKey);
    }

    public Shipment insure(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.POST, String.format("%s/insure",
                        instanceURL(Shipment.class, this.getId())), params,
                Shipment.class, apiKey);
    }

    // lowest rate
    public Rate lowestRate() throws EasyPostException {
        return this.lowestRate(null, null);
    }
  
    public Rate lowestRate(final List<String> carriers) throws EasyPostException {
        return this.lowestRate(carriers, null);
    }

    public Rate lowestRate(final List<String> carriers, final List<String> services)
            throws EasyPostException {
        Rate lowestRate = null;

        if (carriers != null) {
            for (int i = 0; i < carriers.size(); i++) {
                carriers.set(i, carriers.get(i).toLowerCase());
            }
        }

        if (services != null) {
            for (int i = 0; i < services.size(); i++) {
                services.set(i, services.get(i).toLowerCase());
            }
        }

        for (int i = 0; i < this.rates.size(); i++) {
            if (carriers != null && carriers.size() > 0 && !carriers.contains(
                    this.rates.get(i).getCarrier().toLowerCase()) &&
                    !carriers.contains(
                            this.rates.get(i).getServiceCode().toLowerCase())) {
                continue;
            }
            if (services != null && services.size() > 0 && !services.contains(
                    this.rates.get(i).getService().toLowerCase())
                    && !services.contains(
                            this.rates.get(i).getService().toLowerCase())) {
                continue;
            }

            if (lowestRate == null
                    || lowestRate.getRate() > this.rates.get(i).getRate()) {
                lowestRate = this.rates.get(i);
            }
        }

        if (lowestRate == null) {
            throw new EasyPostException(
                    "Unable to find lowest rate matching required criteria.");
        }

        return lowestRate;
    }
}
