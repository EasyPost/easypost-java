package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Order extends EasyPostResource {
    private String id;
    private String mode;
    private String service;
    private String reference;
    private Boolean isReturn;
    private Address toAddress;
    private Address buyerAddress;
    private Address fromAddress;
    private Address returnAddress;
    private CustomsInfo customsInfo;
    private List<Shipment> shipments;
    private List<Rate> rates;
    private Map<String, Object> options;
    private List<ShipmentMessage> messages;
    private List<CarrierAccount> carrierAccounts;

    /**
     * Create an Order object from a map of paramters.
     *
     * @param params map of parameters.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public static Order create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create an Order object from a map of paramters.
     *
     * @param params map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public static Order create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("order", params);

        return request(RequestMethod.POST, classURL(Order.class), wrappedParams, Order.class, apiKey);
    }

    /**
     * Retrieve an Order object from the API.
     *
     * @param id ID of the Order to retrieve.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public static Order retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve an Order object from the API.
     *
     * @param id     ID of the Order to retrieve.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public static Order retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Order.class, id), null, Order.class, apiKey);
    }

    /**
     * Get the ID of the Order.
     *
     * @return the ID of the Order.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the Order.
     *
     * @param id the ID of the Order.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the mode of the Order.
     *
     * @return the mode of the Order.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the mode of the Order.
     *
     * @param mode the mode of the Order.
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Get the service of the Order.
     *
     * @return the service of the Order.
     */
    public String getService() {
        return service;
    }

    /**
     * Set the service of the Order.
     *
     * @param service the service of the Order.
     */
    public void setService(final String service) {
        this.service = service;
    }

    /**
     * Get the reference of the Order.
     *
     * @return the reference of the Order.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Set the reference of the Order.
     *
     * @param reference the reference of the Order.
     */
    public void setReference(final String reference) {
        this.reference = reference;
    }

    /**
     * Get whether the order is a return.
     *
     * @return true if the order is a return.
     */
    public Boolean getIsReturn() {
        return isReturn;
    }

    /**
     * Set whether the order is a return.
     *
     * @param isReturn true if the order is a return.
     */
    public void setIsReturn(final Boolean isReturn) {
        this.isReturn = isReturn;
    }

    /**
     * Get the to address of the Order.
     *
     * @return the to address of the Order.
     */
    public Address getToAddress() {
        return toAddress;
    }

    /**
     * Set the to address of the Order.
     *
     * @param toAddress the to address of the Order.
     */
    public void setToAddress(final Address toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * Get the buyer address of the Order.
     *
     * @return the buyer address of the Order.
     */
    public Address getBuyerAddress() {
        return buyerAddress;
    }

    /**
     * Set the buyer address of the Order.
     *
     * @param buyerAddress the buyer address of the Order.
     */
    public void setBuyerAddress(final Address buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    /**
     * Get the from address of the Order.
     *
     * @return the from address of the Order.
     */
    public Address getFromAddress() {
        return fromAddress;
    }

    /**
     * Set the from address of the Order.
     *
     * @param fromAddress the from address of the Order.
     */
    public void setFromAddress(final Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * Get the return address of the Order.
     *
     * @return the return address of the Order.
     */
    public Address getReturnAddress() {
        return returnAddress;
    }

    /**
     * Set the return address of the Order.
     *
     * @param returnAddress the return address of the Order.
     */
    public void setReturnAddress(final Address returnAddress) {
        this.returnAddress = returnAddress;
    }

    /**
     * Get the customs info of the Order.
     *
     * @return CustomsInfo object.
     */
    public CustomsInfo getCustomsInfo() {
        return customsInfo;
    }

    /**
     * Set the customs info of the Order.
     *
     * @param customsInfo CustomsInfo object.
     */
    public void setCustomsInfo(final CustomsInfo customsInfo) {
        this.customsInfo = customsInfo;
    }

    /**
     * Get the shipments of the Order.
     *
     * @return List of Shipment objects.
     */
    public List<Shipment> getShipments() {
        return shipments;
    }

    /**
     * Set the shipments of the Order.
     *
     * @param shipments List of Shipment objects.
     */
    public void setShipments(final List<Shipment> shipments) {
        this.shipments = shipments;
    }

    /**
     * Get the rates of the Order.
     *
     * @return List of Rate objects.
     */
    public List<Rate> getRates() {
        return rates;
    }

    /**
     * Set the rates of the Order.
     *
     * @param rates List of Rate objects.
     */
    public void setRates(final List<Rate> rates) {
        this.rates = rates;
    }

    /**
     * Get the options of the Order.
     *
     * @return map of options.
     */
    public Map<String, Object> getOptions() {
        return options;
    }

    /**
     * Set the options of the Order.
     *
     * @param options map of options.
     */
    public void setOptions(final Map<String, Object> options) {
        this.options = options;
    }

    /**
     * Get the messages of the Order.
     *
     * @return List of ShipmentMessage objects.
     */
    public List<ShipmentMessage> getMessages() {
        return messages;
    }

    /**
     * Set the messages of the Order.
     *
     * @param messages List of ShipmentMessage objects.
     */
    public void setMessages(final List<ShipmentMessage> messages) {
        this.messages = messages;
    }

    /**
     * Get the list of carrier accounts of the Order.
     *
     * @return List of CarrierAccount object.
     */
    public List<CarrierAccount> getCarrierAccounts() {
        return carrierAccounts;
    }

    /**
     * Set the carrier accounts of the Order.
     *
     * @param carrierAccounts List of CarrierAccount objects
     */
    public void setCarrierAccounts(List<CarrierAccount> carrierAccounts) {
        this.carrierAccounts = carrierAccounts;
    }

    /**
     * Refresh this Order object.
     *
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order refresh() throws EasyPostException {
        return this.refresh(null, null);
    }

    /**
     * Refresh this Order object.
     *
     * @param params map of parameters.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order refresh(final Map<String, Object> params) throws EasyPostException {
        return this.refresh(params, null);
    }

    /**
     * Refresh this Order object.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order refresh(final String apiKey) throws EasyPostException {
        return this.refresh(null, apiKey);
    }

    /**
     * Refresh this Order object.
     *
     * @param params map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order refresh(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, String.format("%s", instanceURL(Order.class, this.getId())), params,
                Order.class, apiKey);
    }

    /**
     * Get new rates for this Order.
     *
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order newRates() throws EasyPostException {
        return this.newRates(null, null);
    }

    /**
     * Get new rates for this Order.
     *
     * @param params map of parameters.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order newRates(final Map<String, Object> params) throws EasyPostException {
        return this.newRates(params, null);
    }

    /**
     * Get new rates for this Order.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order newRates(final String apiKey) throws EasyPostException {
        return this.newRates(null, apiKey);
    }

    /**
     * Get new rates for this Order.
     *
     * @param params map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order newRates(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Order response =
                request(RequestMethod.GET, String.format("%s/rates", instanceURL(Order.class, this.getId())), params,
                        Order.class, apiKey);

        this.merge(this, response);
        return this;
    }

    /**
     * Buy this Order.
     *
     * @param params map of parameters.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order buy(final Map<String, Object> params) throws EasyPostException {
        return this.buy(params, null);
    }

    /**
     * Buy this Order.
     *
     * @param rate Rate to buy.
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order buy(final Rate rate) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("carrier", rate.getCarrier());
        params.put("service", rate.getService());

        return this.buy(params, null);
    }

    /**
     * Buy this Order.
     *
     * @param params map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Order object.
     * @throws EasyPostException when the request fails.
     */
    public Order buy(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Order response =
                request(RequestMethod.POST, String.format("%s/buy", instanceURL(Order.class, this.getId())), params,
                        Order.class, apiKey);

        this.merge(this, response);
        return this;
    }

    /**
     * Get the lowest rate for this order.
     *
     * @return Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate() throws EasyPostException {
        return Utilities.getLowestObjectRate(this.rates, null, null);
    }

    /**
     * Get the lowest rate for this order.
     *
     * @param carriers the carriers to use in the query.
     * @return Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final List<String> carriers) throws EasyPostException {
        return Utilities.getLowestObjectRate(this.rates, carriers, null);
    }

    /**
     * Get the lowest rate for this order.
     *
     * @param carriers the carriers to use in the query.
     * @param services the services to use in the query.
     * @return Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final List<String> carriers, final List<String> services) throws EasyPostException {
        return Utilities.getLowestObjectRate(this.rates, carriers, services);
    }
}
