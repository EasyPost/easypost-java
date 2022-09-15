package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

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
     * @param params           the map of parameters.
     * @param withCarbonOffset whether to include a carbon offset when creating the shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public static Shipment create(final Map<String, Object> params, boolean withCarbonOffset) throws EasyPostException {
        return create(params, withCarbonOffset, null);
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
        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("shipment", params);

        return request(RequestMethod.POST, classURL(Shipment.class), wrappedParams, Shipment.class, apiKey);
    }

    /**
     * Create a new Shipment object from a map of parameters.
     *
     * @param params           the map of parameters.
     * @param withCarbonOffset whether to include a carbon offset when creating the shipment.
     * @param apiKey           API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public static Shipment create(final Map<String, Object> params, boolean withCarbonOffset, final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("shipment", params);
        wrappedParams.put("carbon_offset", withCarbonOffset);

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
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refresh(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, String.format("%s", instanceURL(Shipment.class, this.getId())), params,
                Shipment.class, apiKey);
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
     * Get new rates for this Shipment.
     *
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates() throws EasyPostException {
        return this.newRates(new HashMap<String, Object>() {}, false, null);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param withCarbonOffset whether to include a carbon offset when re-rating the shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final boolean withCarbonOffset) throws EasyPostException {
        return this.newRates(new HashMap<String, Object>() {}, withCarbonOffset, null);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param params the options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final Map<String, Object> params) throws EasyPostException {
        return this.newRates(params, false, null);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param params           the options for the query.
     * @param withCarbonOffset whether to include a carbon offset when re-rating the shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final Map<String, Object> params, final boolean withCarbonOffset)
            throws EasyPostException {
        return this.newRates(params, withCarbonOffset, null);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final String apiKey) throws EasyPostException {
        return this.newRates(new HashMap<String, Object>() {}, false, apiKey);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param withCarbonOffset whether to include a carbon offset when re-rating the shipment.
     * @param apiKey           API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final boolean withCarbonOffset, final String apiKey) throws EasyPostException {
        return this.newRates(new HashMap<String, Object>() {}, withCarbonOffset, apiKey);
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
        return this.newRates(params, false, apiKey);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param params           the options for the query.
     * @param withCarbonOffset whether to include a carbon offset when re-rating the shipment.
     * @param apiKey           API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final Map<String, Object> params, final boolean withCarbonOffset, final String apiKey)
            throws EasyPostException {
        params.put("carbon_offset", withCarbonOffset);
        Shipment response =
                request(RequestMethod.POST, String.format("%s/rerate", instanceURL(Shipment.class, this.getId())),
                        params, Shipment.class, apiKey);

        this.merge(this, response);

        return this;
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param params the options for the query.
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #smartrates(Map)} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public List<Smartrate> getSmartrates(final Map<String, Object> params) throws EasyPostException {
        return this.smartrates(params);
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param params the options for the query.
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Smartrate> smartrates(final Map<String, Object> params) throws EasyPostException {
        return this.smartrates(params, null);
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param params the options for the query.
     * @param apiKey API key to use in request (overrides default API key).
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #smartrates(Map, String)} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public List<Smartrate> getSmartrates(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return this.smartrates(params, apiKey);
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param params the options for the query.
     * @param apiKey API key to use in request (overrides default API key).
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Smartrate> smartrates(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        SmartrateCollection smartrateCollection =
                request(RequestMethod.GET, String.format("%s/smartrate", instanceURL(Shipment.class, this.getId())),
                        params, SmartrateCollection.class, apiKey);
        return smartrateCollection.getSmartrates();
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #smartrates(String)} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public List<Smartrate> getSmartrates(final String apiKey) throws EasyPostException {
        return this.smartrates(apiKey);
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Smartrate> smartrates(final String apiKey) throws EasyPostException {
        return this.smartrates(null, apiKey);
    }

    /**
     * Buy this Shipment.
     *
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy() throws EasyPostException {
        return this.buy(new HashMap<String, Object>() {}, false, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param withCarbonOffset whether to include a carbon offset when buying the shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final boolean withCarbonOffset) throws EasyPostException {
        return this.buy(new HashMap<String, Object>() {}, withCarbonOffset, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param params the options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Map<String, Object> params) throws EasyPostException {
        return this.buy(params, false, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param params           the options for the query.
     * @param withCarbonOffset whether to include a carbon offset when buying the shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Map<String, Object> params, final boolean withCarbonOffset) throws EasyPostException {
        return this.buy(params, withCarbonOffset, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final String apiKey) throws EasyPostException {
        return this.buy(new HashMap<String, Object>() {}, false, apiKey);
    }

    /**
     * Buy this Shipment.
     *
     * @param withCarbonOffset whether to include a carbon offset when buying the shipment.
     * @param apiKey           API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final boolean withCarbonOffset, final String apiKey) throws EasyPostException {
        return this.buy(new HashMap<String, Object>() {}, withCarbonOffset, apiKey);
    }

    /**
     * Buy this Shipment.
     *
     * @param rate the Rate to use for this Shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Rate rate) throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("rate", rate);

        return this.buy(params, false, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param rate             the Rate to use for this Shipment.
     * @param withCarbonOffset whether to include a carbon offset when buying the shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Rate rate, final boolean withCarbonOffset) throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("rate", rate);

        return this.buy(params, withCarbonOffset, null, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param rate the Rate to use for this Shipment.
     * @param endShipperId     the id of the end shipper to use for this purchase.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Rate rate, final String endShipperId) throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("rate", rate);

        return this.buy(params, false, endShipperId, null);
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
      // TODO: When Java Client Library rewrite happens, the apiKey param will be replaced with endShipperId  
      return this.buy(params, false, null, apiKey);
    }

    /**
     * Buy this Shipment.
     *
     * @param withCarbonOffset whether to include a carbon offset when buying the shipment.
     * @param endShipperId     the id of the end shipper to use for this purchase.
     * @param apiKey           API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final boolean withCarbonOffset, final String endShipperId, final String apiKey)
      throws EasyPostException {
      return this.buy(new HashMap<String, Object>() {}, withCarbonOffset, endShipperId, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param params           the options for the query.
     * @param withCarbonOffset whether to include a carbon offset when buying the shipment.
     * @param apiKey           API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Map<String, Object> params, final boolean withCarbonOffset, final String apiKey)
            throws EasyPostException {
        return this.buy(params, withCarbonOffset, null, apiKey);
    }

    /**
     * Buy this Shipment.
     *
     * @param params           the options for the query.
     * @param endShipperId     the id of the end shipper to use for this purchase.
     * @param apiKey           API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Map<String, Object> params, final String endShipperId, final String apiKey)
            throws EasyPostException {
        return this.buy(params, false, endShipperId, apiKey);
    }

    /**
     * Buy this Shipment.
     *
     * @param params           the options for the query.
     * @param withCarbonOffset whether to include a carbon offset when buying the shipment.
     * @param endShipperId     the id of the end shipper to use for this purchase.
     * @param apiKey           API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Map<String, Object> params, final boolean withCarbonOffset,
        final String endShipperId, final String apiKey) throws EasyPostException {
        params.put("carbon_offset", withCarbonOffset);

        if (endShipperId != null && !endShipperId.isEmpty()) {
          params.put("end_shipper_id", endShipperId);
        }

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
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refund(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, String.format("%s/refund", instanceURL(Shipment.class, this.getId())), params,
                Shipment.class, apiKey);
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
     * @param apiKey API key to use in request (overrides default API key).
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment insure(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.POST, String.format("%s/insure", instanceURL(Shipment.class, this.getId())),
                params, Shipment.class, apiKey);
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
     * Get the lowest smartrate for this Shipment.
     *
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when filtering.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     * @deprecated use {@link #lowestSmartRate(int, SmartrateAccuracy)} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public Smartrate lowestSmartRate(int deliveryDay, String deliveryAccuracy) throws EasyPostException {
        return this.lowestSmartRate(deliveryDay, SmartrateAccuracy.getByKeyName(deliveryAccuracy));
    }

    /**
     * Get the lowest smartrate for this Shipment.
     *
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when filtering.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     */
    public Smartrate lowestSmartRate(int deliveryDay, SmartrateAccuracy deliveryAccuracy) throws EasyPostException {
        List<Smartrate> smartrates = this.smartrates();

        Smartrate lowestSmartrate = findLowestSmartrate(smartrates, deliveryDay, deliveryAccuracy);

        return lowestSmartrate;
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #smartrates()} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public List<Smartrate> getSmartrates() throws EasyPostException {
        return this.smartrates();
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Smartrate> smartrates() throws EasyPostException {
        return this.smartrates(null, null);
    }

    /**
     * Get the lowest Smartrate from a list of Smartrates.
     *
     * @param smartrates       List of Smartrates to filter from.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when filtering.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #findLowestSmartrate(List, int, SmartrateAccuracy)} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public static Smartrate getLowestSmartRate(final List<Smartrate> smartrates, int deliveryDay,
                                               String deliveryAccuracy) throws EasyPostException {
        return findLowestSmartrate(smartrates, deliveryDay, SmartrateAccuracy.getByKeyName(deliveryAccuracy));
    }

    /**
     * Find the lowest Smartrate from a list of Smartrates.
     *
     * @param smartrates       List of Smartrates to filter from.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when filtering.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     */
    public static Smartrate findLowestSmartrate(final List<Smartrate> smartrates, int deliveryDay,
                                                SmartrateAccuracy deliveryAccuracy) throws EasyPostException {
        Smartrate lowestSmartrate = null;

        for (Smartrate rate : smartrates) {
            int smartrateDeliveryDay = rate.getTimeInTransit().getBySmartrateAccuracy(deliveryAccuracy);

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
     * Get the lowest rate for this Shipment.
     *
     * @return lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate() throws EasyPostException {
        return this.lowestRate(null, null);
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
     * Generate a form for this shipment.
     *
     * @param formType the form type for this shipment.
     * @throws EasyPostException when the request fails.
     */
    public void generateForm(final String formType) throws EasyPostException {
        this.generateForm(formType, null, null);
    }

    /**
     * Generate a form for this shipment.
     *
     * @param formType the form type for this shipment.
     * @param apiKey   API key to use in request (overrides default API key).
     * @throws EasyPostException when the request fails.
     */
    public void generateForm(final String formType, final String apiKey) throws EasyPostException {
        this.generateForm(formType, null, apiKey);
    }

    /**
     * Generate a form for this shipment.
     *
     * @param formType    the form type for this shipment.
     * @param formOptions the form options for this shipment.
     * @throws EasyPostException when the request fails.
     */
    public void generateForm(final String formType, final Map<String, Object> formOptions) throws EasyPostException {
        this.generateForm(formType, formOptions, null);
    }

    /**
     * Generate a form for this shipment.
     *
     * @param formType    the form type for this shipment.
     * @param formOptions the form options for this shipment.
     * @param apiKey      API key to use in request (overrides default API key).
     * @throws EasyPostException when the request fails.
     */
    public void generateForm(final String formType, final Map<String, Object> formOptions, String apiKey)
            throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        HashMap<String, Object> wrappedParams = new HashMap<>();

        params.put("type", formType);
        params.putAll(formOptions);
        wrappedParams.put("form", params);

        Shipment response =
                request(RequestMethod.POST, String.format("%s/forms", instanceURL(Shipment.class, this.getId())),
                        wrappedParams, Shipment.class, apiKey);

        this.merge(this, response);
    }
}
