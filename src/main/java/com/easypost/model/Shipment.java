package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Shipment extends EasyPostResource {
    private String id;
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
    private List<CarrierAccount> carrierAccounts;
    private String service;

    /**
     * Create a new Shipment object from a map of parameters.
     *
     * @param params the map of parameters.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public static Shipment create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a new Shipment object from a map of parameters.
     *
     * @param params the map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public static Shipment create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("shipment", params);

        return request(RequestMethod.POST, classURL(Shipment.class), wrappedParams, Shipment.class, apiKey);
    }

    /**
     * Retrieve a Shipment from the API.
     *
     * @param id the id of the Shipment to retrieve.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public static Shipment retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Shipment from the API.
     *
     * @param id     the id of the Shipment to retrieve.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public static Shipment retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Shipment.class, id), null, Shipment.class, apiKey);
    }

    /**
     * Get a list of all Shipment objects.
     *
     * @param params the options for the query.
     * @return ShipmentCollection object
     * @throws EasyPostException when the request fails.
     */
    public static ShipmentCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * Get a list of all Shipment objects.
     *
     * @param params the options for the query.
     * @param apiKey API key to use in request (overrides default API key).
     * @return ShipmentCollection object
     * @throws EasyPostException when the request fails.
     */
    public static ShipmentCollection all(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Shipment.class), params, ShipmentCollection.class, apiKey);
    }

    /**
     * Get the lowest smartrate from a list of smartrates.
     *
     * @param smartrates       List of smartrates to filter from.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when filtering.
     * @return lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public static Rate getLowestSmartRate(final List<Rate> smartrates, int deliveryDay, String deliveryAccuracy)
            throws EasyPostException {
        Rate lowestSmartrate = null;

        String[] validDeliveryAccuracies = new String[] {
                "percentile_50",
                "percentile_75",
                "percentile_85",
                "percentile_90",
                "percentile_95",
                "percentile_97",
                "percentile_99"
        };

        if (Arrays.stream(validDeliveryAccuracies).noneMatch(deliveryAccuracy::equals)) {
            throw new EasyPostException("Invalid delivery_accuracy value");
        }

        for (Rate rate : smartrates) {
            int smartrateDeliveryDay = rate.getTimeInTransit().getSmartRateAccuracy(deliveryAccuracy);

            if (smartrateDeliveryDay > deliveryDay) {
                continue;
            } else if (lowestSmartrate == null || rate.getRate() < lowestSmartrate.getRate()) {
                lowestSmartrate = rate;
            }
        }

        if (lowestSmartrate == null) {
            throw new EasyPostException("No rates found.");
        }

        return lowestSmartrate;
    }

    /**
     * Get the ID of this Shipment.
     *
     * @return the ID of this Shipment.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of this Shipment.
     *
     * @param id the ID of this Shipment.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the mode of this Shipment.
     *
     * @return the mode of this Shipment.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the mode of this Shipment.
     *
     * @param mode the mode of this Shipment.
     */
    public void setMode(final String mode) {
        this.mode = mode;
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
     * Get the status of this Shipment.
     *
     * @return the status of this Shipment.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status of this Shipment.
     *
     * @param status the status of this Shipment.
     */
    public void setStatus(final String status) {
        this.status = status;
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
     * Refresh this Shipment.
     *
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refresh() throws EasyPostException {
        return this.refresh(null, null);
    }

    /**
     * Refresh this Shipment.
     *
     * @param params the options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refresh(final Map<String, Object> params) throws EasyPostException {
        return this.refresh(params, null);
    }

    /**
     * Refresh this Shipment.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refresh(final String apiKey) throws EasyPostException {
        return this.refresh(null, apiKey);
    }

    /**
     * Refresh this Shipment.
     *
     * @param params the options for the query.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refresh(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, String.format("%s", instanceURL(Shipment.class, this.getId())), params,
                Shipment.class, apiKey);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates() throws EasyPostException {
        return this.newRates(null, null);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param params the options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final Map<String, Object> params) throws EasyPostException {
        return this.newRates(params, null);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final String apiKey) throws EasyPostException {
        return this.newRates(null, apiKey);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param params the options for the query.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Shipment response =
                request(RequestMethod.POST, String.format("%s/rerate", instanceURL(Shipment.class, this.getId())),
                        params, Shipment.class, apiKey);

        this.merge(this, response);
        return this;
    }

    /**
     * Get smart rates for this Shipment.
     *
     * @return List of Rate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Rate> getSmartrates() throws EasyPostException {
        return this.getSmartrates(null, null);
    }

    /**
     * Get smart rates for this Shipment.
     *
     * @param params the options for the query.
     * @return List of Rate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Rate> getSmartrates(final Map<String, Object> params) throws EasyPostException {
        return this.getSmartrates(params, null);
    }

    /**
     * Get smart rates for this Shipment.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return List of Rate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Rate> getSmartrates(final String apiKey) throws EasyPostException {
        return this.getSmartrates(null, apiKey);
    }

    /**
     * Get smart rates for this Shipment.
     *
     * @param params the options for the query.
     * @param apiKey API key to use in request (overrides default API key).
     * @return List of Rate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Rate> getSmartrates(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        SmartrateCollection smartrateCollection =
                request(RequestMethod.GET, String.format("%s/smartrate", instanceURL(Shipment.class, this.getId())),
                        params, SmartrateCollection.class, apiKey);

        return smartrateCollection.getRates();
    }

    /**
     * Buy this Shipment.
     *
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy() throws EasyPostException {
        return this.buy(null, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param params the options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Map<String, Object> params) throws EasyPostException {
        return this.buy(params, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final String apiKey) throws EasyPostException {
        return this.buy(null, apiKey);
    }

    /**
     * Buy this Shipment.
     *
     * @param rate the Rate to use for this Shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Rate rate) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rate", rate);

        return this.buy(params, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param params the options for the query.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Shipment response =
                request(RequestMethod.POST, String.format("%s/buy", instanceURL(Shipment.class, this.getId())), params,
                        Shipment.class, apiKey);

        this.merge(this, response);
        return this;
    }

    /**
     * Refund this Shipment.
     *
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refund() throws EasyPostException {
        return this.refund(null, null);
    }

    /**
     * Refund this Shipment.
     *
     * @param params the options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refund(final Map<String, Object> params) throws EasyPostException {
        return this.refund(params, null);
    }

    /**
     * Refund this Shipment.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refund(final String apiKey) throws EasyPostException {
        return this.refund(null, apiKey);
    }

    /**
     * Refund this Shipment.
     *
     * @param params the options for the query.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refund(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, String.format("%s/refund", instanceURL(Shipment.class, this.getId())), params,
                Shipment.class, apiKey);
    }

    /**
     * Label this Shipment.
     *
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment label() throws EasyPostException {
        return this.label(null, null);
    }

    /**
     * Label this Shipment.
     *
     * @param params the options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment label(final Map<String, Object> params) throws EasyPostException {
        return this.label(params, null);
    }

    /**
     * Label this Shipment.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment label(final String apiKey) throws EasyPostException {
        return this.label(null, apiKey);
    }

    /**
     * Label this Shipment.
     *
     * @param params the options for the query.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment label(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Shipment response =
                request(RequestMethod.GET, String.format("%s/label", instanceURL(Shipment.class, this.getId())), params,
                        Shipment.class, apiKey);

        this.merge(this, response);
        return this;
    }

    /**
     * Insure this Shipment.
     *
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment insure() throws EasyPostException {
        return this.insure(null, null);
    }

    /**
     * Insure this Shipment.
     *
     * @param params the options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment insure(final Map<String, Object> params) throws EasyPostException {
        return this.insure(params, null);
    }

    /**
     * Insure this Shipment.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment insure(final String apiKey) throws EasyPostException {
        return this.insure(null, apiKey);
    }

    /**
     * Insure this Shipment.
     *
     * @param params the options for the query.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment insure(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.POST, String.format("%s/insure", instanceURL(Shipment.class, this.getId())),
                params, Shipment.class, apiKey);
    }

    /**
     * Get the lowest smartrate for this Shipment.
     *
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when filtering.
     * @return lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestSmartRate(int deliveryDay, String deliveryAccuracy) throws EasyPostException {
        List<Rate> smartrates = this.getSmartrates();

        Rate lowestSmartrate = getLowestSmartRate(smartrates, deliveryDay, deliveryAccuracy);

        return lowestSmartrate;
    }

    /**
     * Get the lowest rate for this Shipment.
     *
     * @return lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate() throws EasyPostException {
        return this.lowestRate(null, null);
    }

    /**
     * Get the lowest rate for this shipment.
     *
     * @param carriers the carriers to use in the query.
     * @return Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final List<String> carriers) throws EasyPostException {
        return this.lowestRate(carriers, null);
    }

    /**
     * Get the lowest rate for this Shipment.
     *
     * @param carriers the carriers to use in the filter.
     * @param services the services to use in the filter.
     * @return lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final List<String> carriers, final List<String> services) throws EasyPostException {
        return Utilities.getLowestObjectRate(this.rates, carriers, services);
    }
}
