/**
 * Rate.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public final class Rate extends EasyPostResource {
    public String id;
    private String carrier;
    private String service;
    private String serviceCode;
    private Float rate;
    private String currency;
    private Float listRate;
    private String listCurrency;
    private Float retailRate;
    private String retailCurrency;
    private Number deliveryDays;
    private String deliveryDate;
    private Boolean deliveryDateGuaranteed;
    private Number estDeliveryDays;
    private String shipmentId;
    private String carrierAccountId;
    private TimeInTransit timeInTransit;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    public String getService() {
        return service;
    }

    public void setService(final String service) {
        this.service = service;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(final String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(final Float rate) {
        this.rate = rate;
    }

    public Float getListRate() {
        return listRate;
    }

    public void setListRate(final Float listRate) {
        this.listRate = listRate;
    }

    public Float getRetailRate() {
        return retailRate;
    }

    public void setRetailRate(final Float retailRate) {
        this.retailRate = retailRate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getListCurrency() {
        return listCurrency;
    }

    public void setListCurrency(final String listCurrency) {
        this.listCurrency = listCurrency;
    }

    public String getRetailCurrency() {
        return retailCurrency;
    }

    public void setRetailCurrency(final String retailCurrency) {
        this.retailCurrency = retailCurrency;
    }

    public Number getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(final Number deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(final String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Boolean getDeliveryDateGuaranteed() {
        return deliveryDateGuaranteed;
    }

    public void setDeliveryDateGuaranteed(
            final Boolean deliveryDateGuaranteed) {
        this.deliveryDateGuaranteed = deliveryDateGuaranteed;
    }

    public Number getEstDeliveryDays() {
        return estDeliveryDays;
    }

    public void setEstDeliveryDays(final Number estDeliveryDays) {
        this.estDeliveryDays = estDeliveryDays;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(final String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getCarrierAccountId() {
        return carrierAccountId;
    }

    public void setCarrierAccountId(final String carrierAccountId) {
        this.carrierAccountId = carrierAccountId;
    }

    public TimeInTransit getTimeInTransit() {
        return timeInTransit;
    }

    public void setTimeInTransit(final TimeInTransit timeInTransit) {
        this.timeInTransit = timeInTransit;
    }

    public Rate(String id, String carrier, String service, Float rate,
                String currency, Float listRate, String listCurrency,
                Float retailRate, String retailCurrency, Number deliveryDays,
                String deliveryDate, Boolean deliveryDateGuaranteed,
                Number estDeliveryDays, String shipmentId,
                String carrierAccountId, TimeInTransit timeInTransit) {
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
        this.timeInTransit = timeInTransit;
    }

    // retrieve
    public static Rate retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static Rate retrieve(final String id, final String apiKey)
            throws EasyPostException {
        Rate response;
        response = request(RequestMethod.GET, instanceURL(Rate.class, id), null,
                Rate.class, apiKey);

        return response;
    }
}
