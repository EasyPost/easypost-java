/**
 * Order.java
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

public final class Order extends EasyPostResource {
    public String id;
    private String mode;
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

    public CustomsInfo getCustomsInfo() {
        return customsInfo;
    }

    public void setCustomsInfo(final CustomsInfo customsInfo) {
        this.customsInfo = customsInfo;
    }

    public List<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(final List<Shipment> shipments) {
        this.shipments = shipments;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(final List<Rate> rates) {
        this.rates = rates;
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


    // create
    public static Order create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static Order create(final Map<String, Object> params,
                               final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("order", params);

        return request(RequestMethod.POST, classURL(Order.class), wrappedParams,
                Order.class, apiKey);
    }

    // retrieve
    public static Order retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static Order retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Order.class, id), null,
                Order.class, apiKey);
    }

    // refresh
    public Order refresh() throws EasyPostException {
        return this.refresh(null, null);
    }

    public Order refresh(final Map<String, Object> params)
            throws EasyPostException {
        return this.refresh(params, null);
    }

    public Order refresh(final String apiKey) throws EasyPostException {
        return this.refresh((Map<String, Object>) null, apiKey);
    }

    public Order refresh(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET,
                String.format("%s", instanceURL(Order.class, this.getId())),
                params, Order.class, apiKey);
    }

    // get rates
    public Order newRates() throws EasyPostException {
        return this.newRates(null, null);
    }

    public Order newRates(final Map<String, Object> params)
            throws EasyPostException {
        return this.newRates(params, null);
    }

    public Order newRates(final String apiKey) throws EasyPostException {
        return this.newRates((Map<String, Object>) null, apiKey);
    }

    public Order newRates(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        Order response = request(RequestMethod.GET, String.format("%s/rates",
                        instanceURL(Order.class, this.getId())), params, Order.class,
                apiKey);

        this.merge(this, response);
        return this;
    }

    // buy
    public Order buy(final Map<String, Object> params)
            throws EasyPostException {
        return this.buy(params, null);
    }

    public Order buy(final Rate rate) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("carrier", rate.getCarrier());
        params.put("service", rate.getService());

        return this.buy(params, null);
    }

    public Order buy(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        Order response = request(RequestMethod.POST,
                String.format("%s/buy", instanceURL(Order.class, this.getId())),
                params, Order.class, apiKey);

        this.merge(this, response);
        return this;
    }

}
