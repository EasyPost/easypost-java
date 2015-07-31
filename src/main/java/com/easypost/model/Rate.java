package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Rate extends EasyPostResource {
  public String id;
  String carrier;
  String service;
  String serviceCode;
  Float rate;
  String currency;
  Float listRate;
  String listCurrency;
  Float retailRate;
  String retailCurrency;
  Number deliveryDays;
  String deliveryDate;
  Boolean deliveryDateGuaranteed;
  Number estDeliveryDays;
  String shipmentId;
  String carrierAccountId;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getCarrier() {
    return carrier;
  }
  public void setCarrier(String carrier) {
    this.carrier = carrier;
  }

  public String getService() {
          return service;
  }
  public void setService(String service) {
          this.service = service;
  }

  public String getServiceCode() {
    return serviceCode;
  }
  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  public Float getRate() {
    return rate;
  }
  public void setRate(Float rate) {
    this.rate = rate;
  }

  public Float getListRate() {
    return listRate;
  }
  public void setListRate(Float listRate) {
    this.listRate = listRate;
  }

  public Float getRetailRate() {
    return retailRate;
  }
  public void setRetailRate(Float retailRate) {
    this.retailRate = retailRate;
  }

  public String getCurrency() {
    return currency;
  }
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getListCurrency() {
    return listCurrency;
  }
  public void setListCurrency(String listCurrency) {
    this.listCurrency = listCurrency;
  }

  public String getRetailCurrency() {
    return retailCurrency;
  }
  public void setRetailCurrency(String retailCurrency) {
    this.retailCurrency = retailCurrency;
  }

  public Number getDeliveryDays() {
    return deliveryDays;
  }
  public void setDeliveryDays(Number deliveryDays) {
    this.deliveryDays = deliveryDays;
  }

  public String getDeliveryDate() {
    return deliveryDate;
  }
  public void setDeliveryDate(String deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public Boolean getDeliveryDateGuaranteed() {
    return deliveryDateGuaranteed;
  }
  public void setDeliveryDateGuaranteed(Boolean deliveryDateGuaranteed) {
    this.deliveryDateGuaranteed = deliveryDateGuaranteed;
  }

  public Number getEstDeliveryDays() {
    return estDeliveryDays;
  }
  public void setEstDeliveryDays(Number estDeliveryDays) {
    this.estDeliveryDays = estDeliveryDays;
  }

  public String getShipmentId() {
    return shipmentId;
  }
  public void setShipmentId(String shipmentId) {
    this.shipmentId = shipmentId;
  }

  public String getCarrierAccountId() {
    return carrierAccountId;
  }
  public void setCarrierAccountId(String carrierAccountId) {
    this.carrierAccountId = carrierAccountId;
  }


  public Rate(String id, String carrier, String service, Float rate, String currency, Float listRate, String listCurrency, Float retailRate, String retailCurrency, Number deliveryDays, String deliveryDate, Boolean deliveryDateGuaranteed, Number estDeliveryDays, String shipmentId, String carrierAccountId) {
    this.id = id;
    this.carrier = carrier;
    this.service = service;
    this.serviceCode = carrier.toLowerCase() + "." + service.toLowerCase();
    this.rate = rate;
    this.currency = currency;
    this.listRate = listRate;
    this.listCurrency = listCurrency;
    this.retailRate = retailRate;
    this.retailCurrency = retailCurrency;
    this.deliveryDays = deliveryDays;
    this.deliveryDate = deliveryDate;
    this.deliveryDateGuaranteed = deliveryDateGuaranteed;
    this.estDeliveryDays = estDeliveryDays;
    this.shipmentId = shipmentId;
    this.carrierAccountId = carrierAccountId;
  }

  // retrieve
  public static Rate retrieve(String id) throws EasyPostException {
    return retrieve(id, null);
  }
  public static Rate retrieve(String id, String apiKey) throws EasyPostException {
    Rate response;
    response = request(RequestMethod.GET, instanceURL(Rate.class, id), null, Rate.class, apiKey);

    return response;
  }
}
