package com.easypost.model;

import java.util.List;
import java.util.Map;

public final class Shipment extends EasyPostResource {
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
    private List<CarrierAccount> carrierAccounts;
    private String service;
    private List<Fee> fees;

    /**
     * Get the batch ID of this Shipment.
     *
     * @return the batch ID of this Shipment.
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * Set the batch ID of this Shipment.
     *
     * @param batchId the batch ID of this Shipment.
     */
    public void setBatchId(final String batchId) {
        this.batchId = batchId;
    }

    /**
     * Get the batch message of this Shipment.
     *
     * @return the batch message of this Shipment.
     */
    public String getBatchMessage() {
        return batchMessage;
    }

    /**
     * Set the batch message of this Shipment.
     *
     * @param batchMessage the batch message of this Shipment.
     */
    public void setBatchMessage(final String batchMessage) {
        this.batchMessage = batchMessage;
    }

    /**
     * Get the batch status of this Shipment.
     *
     * @return the batch status of this Shipment.
     */
    public String getBatchStatus() {
        return batchStatus;
    }

    /**
     * Set the batch status of this Shipment.
     *
     * @param batchStatus the batch status of this Shipment.
     */
    public void setBatchStatus(final String batchStatus) {
        this.batchStatus = batchStatus;
    }

    /**
     * Get the buyer address of this Shipment.
     *
     * @return Address object
     */
    public Address getBuyerAddress() {
        return buyerAddress;
    }

    /**
     * Set the buyer address of this Shipment.
     *
     * @param buyerAddress the buyer address of this Shipment.
     */
    public void setBuyerAddress(final Address buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    /**
     * Get the carrier accounts of this shipment.
     *
     * @return List of carrier accounts.
     */
    public List<CarrierAccount> getCarrierAccounts() {
        return carrierAccounts;
    }

    /**
     * Set the carrier accounts list.
     *
     * @param carrierAccounts the list of carrier accounts.
     */
    public void setCarrierAccounts(final List<CarrierAccount> carrierAccounts) {
        this.carrierAccounts = carrierAccounts;
    }

    /**
     * Get the customs info of this Shipment.
     *
     * @return CustomsInfo object
     */
    public CustomsInfo getCustomsInfo() {
        return customsInfo;
    }

    /**
     * Set the customs info of this Shipment.
     *
     * @param customsInfo the customs info of this Shipment.
     */
    public void setCustomsInfo(final CustomsInfo customsInfo) {
        this.customsInfo = customsInfo;
    }

    /**
     * Get the forms of this Shipment.
     *
     * @return List of Form objects
     */
    public List<Form> getForms() {
        return forms;
    }

    /**
     * Set the forms of this Shipment.
     *
     * @param forms the forms of this Shipment.
     */
    public void setForms(final List<Form> forms) {
        this.forms = forms;
    }

    /**
     * Get the from address of this Shipment.
     *
     * @return Address object
     */
    public Address getFromAddress() {
        return fromAddress;
    }

    /**
     * Set the from address of this Shipment.
     *
     * @param fromAddress the from address of this Shipment.
     */
    public void setFromAddress(final Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * Get the insurance of this Shipment.
     *
     * @return the insurance of this Shipment.
     */
    public String getInsurance() {
        return insurance;
    }

    /**
     * Set the insurance of this Shipment.
     *
     * @param insurance the insurance of this Shipment.
     */
    public void setInsurance(final String insurance) {
        this.insurance = insurance;
    }

    /**
     * Get whether this Shipment is a return shipment.
     *
     * @return whether this Shipment is a return shipment.
     */
    public Boolean getIsReturn() {
        return isReturn;
    }

    /**
     * Set whether this Shipment is a return shipment.
     *
     * @param isReturn whether this Shipment is a return shipment.
     */
    public void setIsReturn(final Boolean isReturn) {
        this.isReturn = isReturn;
    }

    /**
     * Get the messages of this Shipment.
     *
     * @return List of ShipmentMessage objects
     */
    public List<ShipmentMessage> getMessages() {
        return messages;
    }

    /**
     * Set the messages of this Shipment.
     *
     * @param messages the messages of this Shipment.
     */
    public void setMessages(final List<ShipmentMessage> messages) {
        this.messages = messages;
    }

    /**
     * Get the options of this Shipment.
     *
     * @return the options of this Shipment.
     */
    public Map<String, Object> getOptions() {
        return options;
    }

    /**
     * Set the options of this Shipment.
     *
     * @param options the options of this Shipment.
     */
    public void setOptions(final Map<String, Object> options) {
        this.options = options;
    }

    /**
     * Get the ID of the order of this Shipment.
     *
     * @return the ID of the order of this Shipment.
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Set the ID of the order of this Shipment.
     *
     * @param orderId the ID of the order of this Shipment.
     */
    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    /**
     * Get the parcel of this Shipment.
     *
     * @return Parcel object
     */
    public Parcel getParcel() {
        return parcel;
    }

    /**
     * Set the parcel of this Shipment.
     *
     * @param parcel the parcel of this Shipment.
     */
    public void setParcel(final Parcel parcel) {
        this.parcel = parcel;
    }

    /**
     * Get the postage label of this Shipment.
     *
     * @return PostageLabel object
     */
    public PostageLabel getPostageLabel() {
        return postageLabel;
    }

    /**
     * Set the postage label of this Shipment.
     *
     * @param postageLabel the postage label of this Shipment.
     */
    public void setPostageLabel(final PostageLabel postageLabel) {
        this.postageLabel = postageLabel;
    }

    /**
     * Get all rates of this Shipment.
     *
     * @return List of Rate objects
     */
    public List<Rate> getRates() {
        return rates;
    }

    /**
     * Set all rates of this Shipment.
     *
     * @param rates the rates of this Shipment.
     */
    public void setRates(final List<Rate> rates) {
        this.rates = rates;
    }

    /**
     * Get the reference of this Shipment.
     *
     * @return the reference of this Shipment.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Set the reference of this Shipment.
     *
     * @param reference the reference of this Shipment.
     */
    public void setReference(final String reference) {
        this.reference = reference;
    }

    /**
     * Get the refund status of this Shipment.
     *
     * @return the refund status of this Shipment.
     */
    public String getRefundStatus() {
        return refundStatus;
    }

    /**
     * Set the refund status of this Shipment.
     *
     * @param refundStatus the refund status of this Shipment.
     */
    public void setRefundStatus(final String refundStatus) {
        this.refundStatus = refundStatus;
    }

    /**
     * Get the return address of this Shipment.
     *
     * @return Address object
     */
    public Address getReturnAddress() {
        return returnAddress;
    }

    /**
     * Set the return address of this Shipment.
     *
     * @param returnAddress the return address of this Shipment.
     */
    public void setReturnAddress(final Address returnAddress) {
        this.returnAddress = returnAddress;
    }

    /**
     * Get the scan form of this Shipment.
     *
     * @return ScanForm object
     */
    public ScanForm getScanForm() {
        return scanForm;
    }

    /**
     * Set the scan form of this Shipment.
     *
     * @param scanForm the scan form of this Shipment.
     */
    public void setScanForm(final ScanForm scanForm) {
        this.scanForm = scanForm;
    }

    /**
     * Get the selected rate of this Shipment.
     *
     * @return Rate object
     */
    public Rate getSelectedRate() {
        return selectedRate;
    }

    /**
     * Set the selected rate of this Shipment.
     *
     * @param selectedRate the selected rate of this Shipment.
     */
    public void setSelectedRate(final Rate selectedRate) {
        this.selectedRate = selectedRate;
    }

    /**
     * Get the service used.
     *
     * @return the service used.
     */
    public String getService() {
        return service;
    }

    /**
     * Set the service used.
     *
     * @param service the service.
     */
    public void setService(final String service) {
        this.service = service;
    }

    /**
     * Get the tax identifiers of this Shipment.
     *
     * @return List of TaxIdentifier objects
     */
    public List<TaxIdentifier> getTaxIdentifiers() {
        return taxIdentifiers;
    }

    /**
     * Set the tax identifiers of this Shipment.
     *
     * @param taxIdentifiers the tax identifiers of this Shipment.
     */
    public void setTaxIdentifiers(final List<TaxIdentifier> taxIdentifiers) {
        this.taxIdentifiers = taxIdentifiers;
    }

    /**
     * Get the to address of this Shipment.
     *
     * @return Address object
     */
    public Address getToAddress() {
        return toAddress;
    }

    /**
     * Set the to address of this Shipment.
     *
     * @param toAddress the to address of this Shipment.
     */
    public void setToAddress(final Address toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * Get the tracker of this Shipment.
     *
     * @return Tracker object
     */
    public Tracker getTracker() {
        return tracker;
    }

    /**
     * Set the tracker of this Shipment.
     *
     * @param tracker the tracker of this Shipment.
     */
    public void setTracker(final Tracker tracker) {
        this.tracker = tracker;
    }

    /**
     * Get the USPS zone of this Shipment.
     *
     * @return the USPS zone of this Shipment.
     */
    public String getUspsZone() {
        return uspsZone;
    }

    /**
     * Set the USPS zone of this Shipment.
     *
     * @param uspsZone the USPS zone of this Shipment.
     */
    public void setUspsZone(final String uspsZone) {
        this.uspsZone = uspsZone;
    }

    /**
     * @return the Fees associated with this object
     */
    public List<Fee> getFees() {
        return fees;
    }

    /**
     * Set the Fees associated with this object.
     *
     * @param fees the Fees associated with this object
     */
    public void setFees(final List<Fee> fees) {
        this.fees = fees;
    }

    /**
     * Get the status of this Shipment.
     *
     * @return the status of this Shipment.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the tracking code of this Shipment.
     *
     * @return the tracking code of this Shipment.
     */
    public String getTrackingCode() {
        return trackingCode;
    }

    /**
     * Set the tracking code of this Shipment.
     *
     * @param trackingCode the tracking code of this Shipment.
     */
    public void setTrackingCode(final String trackingCode) {
        this.trackingCode = trackingCode;
    }

    /**
     * Set the status of this Shipment.
     *
     * @param status the status of this Shipment.
     */
    public void setStatus(final String status) {
        this.status = status;
    }
}
