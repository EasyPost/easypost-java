package com.easypost.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Order extends EasyPostResource {
  public String id;
  String mode;
  String reference;
  Boolean isReturn;
  Address toAddress;
  Address buyerAddress;
  Address fromAddress;
  Address returnAddress;
  CustomsInfo customsInfo;
  List<Shipment> shipments;
  List<Rate> rates;
  List<Container> containers;
  List<Item> items;
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

  public CustomsInfo getCustomsInfo() {
    return customsInfo;
  }
  public void setCustomsInfo(CustomsInfo customsInfo) {
    this.customsInfo = customsInfo;
  }

  public List<Shipment> getShipments() {
    return shipments;
  }
  public void setShipments(List<Shipment> shipments) {
    this.shipments = shipments;
  }

  public List<Rate> getRates() {
    return rates;
  }
  public void setRates(List<Rate> rates) {
    this.rates = rates;
  }

  public List<Container> getContainers() {
    return containers;
  }
  public void setContainers(List<Container> containers) {
    this.containers = containers;
  }

  public List<Item> getItems() {
    return items;
  }
  public void setItems(List<Item> items) {
    this.items = items;
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


  // create
  public static Order create(Map<String, Object> params) throws EasyPostException {
    return create(params, null);
  }
  public static Order create(Map<String, Object> params, String apiKey) throws EasyPostException {
    Map<String, Object> wrappedParams = new HashMap<String, Object>();
    wrappedParams.put("order", params);

    return request(RequestMethod.POST, classURL(Order.class), wrappedParams, Order.class, apiKey);
  }

  // retrieve
  public static Order retrieve(String id) throws EasyPostException {
    return retrieve(id, null);
  }
  public static Order retrieve(String id, String apiKey) throws EasyPostException {
    return request(RequestMethod.GET, instanceURL(Order.class, id), null, Order.class, apiKey);
  }

  // refresh
  public Order refresh() throws EasyPostException {
    return this.refresh(null, null);
  }
  public Order refresh(Map<String, Object> params) throws EasyPostException {
    return this.refresh(params, null);
  }
  public Order refresh(String apiKey) throws EasyPostException {
    return this.refresh((Map<String, Object>) null, apiKey);
  }
  public Order refresh(Map<String, Object> params, String apiKey) throws EasyPostException {
    return request(
      RequestMethod.GET,
      String.format("%s", instanceURL(Order.class, this.getId())), params, Order.class, apiKey);
  }

  // get rates
  public Order newRates() throws EasyPostException {
    return this.newRates(null, null);
  }
  public Order newRates(Map<String, Object> params) throws EasyPostException {
    return this.newRates(params, null);
  }
  public Order newRates(String apiKey) throws EasyPostException {
    return this.newRates((Map<String, Object>) null, apiKey);
  }
  public Order newRates(Map<String, Object> params, String apiKey) throws EasyPostException {
    Order response = request(
            RequestMethod.GET,
            String.format("%s/rates", instanceURL(Order.class, this.getId())), params, Order.class, apiKey);

    this.merge(this, response);
    return this;
  }

  // buy
  public Order buy(Map<String, Object> params) throws EasyPostException {
    return this.buy(params, null);
  }
  public Order buy(Rate rate) throws EasyPostException {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("carrier", rate.carrier);
    params.put("service", rate.service);

    return this.buy(params, null);
  }
  public Order buy(Map<String, Object> params, String apiKey) throws EasyPostException {
    Order response = request(
      RequestMethod.POST,
      String.format("%s/buy", instanceURL(Order.class, this.getId())), params, Order.class, apiKey);

    this.merge(this, response);
      return this;
  }

}
