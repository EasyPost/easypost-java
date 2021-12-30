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

    /**
     * Get the ID of this Rate.
     *
     * @return ID of this Rate.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of this Rate.
     *
     * @param id ID of this Rate.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the carrier of this Rate.
     *
     * @return Carrier of this Rate.
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Set the carrier of this Rate.
     *
     * @param carrier Carrier of this Rate.
     */
    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    /**
     * Get the service of this Rate.
     *
     * @return Service of this Rate.
     */
    public String getService() {
        return service;
    }

    /**
     * Set the service of this Rate.
     *
     * @param service Service of this Rate.
     */
    public void setService(final String service) {
        this.service = service;
    }

    /**
     * Get the service code of this Rate.
     *
     * @return Service code of this Rate.
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Set the service code of this Rate.
     *
     * @param serviceCode Service code of this Rate.
     */
    public void setServiceCode(final String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * Get the rate of this Rate.
     *
     * @return Rate of this Rate.
     */
    public Float getRate() {
        return rate;
    }

    /**
     * Set the rate of this Rate.
     *
     * @param rate Rate of this Rate.
     */
    public void setRate(final Float rate) {
        this.rate = rate;
    }

    /**
     * Get the list rate of this Rate.
     *
     * @return List rate of this Rate.
     */
    public Float getListRate() {
        return listRate;
    }

    /**
     * Set the list rate of this Rate.
     *
     * @param listRate List rate of this Rate.
     */
    public void setListRate(final Float listRate) {
        this.listRate = listRate;
    }

    /**
     * Get the retail rate of this Rate.
     *
     * @return Retail rate of this Rate.
     */
    public Float getRetailRate() {
        return retailRate;
    }

    /**
     * Set the retail rate of this Rate.
     *
     * @param retailRate Retail rate of this Rate.
     */
    public void setRetailRate(final Float retailRate) {
        this.retailRate = retailRate;
    }

    /**
     * Get the currency of this Rate.
     *
     * @return Currency of this Rate.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Set the currency of this Rate.
     *
     * @param currency Currency of this Rate.
     */
    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    /**
     * Get the list currency of this Rate.
     *
     * @return List currency of this Rate.
     */
    public String getListCurrency() {
        return listCurrency;
    }

    /**
     * Set the list currency of this Rate.
     *
     * @param listCurrency List currency of this Rate.
     */
    public void setListCurrency(final String listCurrency) {
        this.listCurrency = listCurrency;
    }

    /**
     * Get the retail currency of this Rate.
     *
     * @return Retail currency of this Rate.
     */
    public String getRetailCurrency() {
        return retailCurrency;
    }

    /**
     * Set the retail currency of this Rate.
     *
     * @param retailCurrency Retail currency of this Rate.
     */
    public void setRetailCurrency(final String retailCurrency) {
        this.retailCurrency = retailCurrency;
    }

    /**
     * Get the delivery days of this Rate.
     *
     * @return Delivery days of this Rate.
     */
    public Number getDeliveryDays() {
        return deliveryDays;
    }

    /**
     * Set the delivery days of this Rate.
     *
     * @param deliveryDays Delivery days of this Rate.
     */
    public void setDeliveryDays(final Number deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    /**
     * Get the delivery date of this Rate.
     *
     * @return Delivery date of this Rate.
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Set the delivery date of this Rate.
     *
     * @param deliveryDate Delivery date of this Rate.
     */
    public void setDeliveryDate(final String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * Get if the delivery date is guaranteed for this Rate.
     *
     * @return true if the delivery date is guaranteed for this Rate.
     */
    public Boolean getDeliveryDateGuaranteed() {
        return deliveryDateGuaranteed;
    }

    /**
     * Set if the delivery date is guaranteed for this Rate.
     *
     * @param deliveryDateGuaranteed true if the delivery date is guaranteed for this Rate.
     */
    public void setDeliveryDateGuaranteed(final Boolean deliveryDateGuaranteed) {
        this.deliveryDateGuaranteed = deliveryDateGuaranteed;
    }

    /**
     * Get the estimated delivery days for this Rate.
     *
     * @return Estimated delivery days for this Rate.
     */
    public Number getEstDeliveryDays() {
        return estDeliveryDays;
    }

    /**
     * Set the estimated delivery days for this Rate.
     *
     * @param estDeliveryDays Estimated delivery days for this Rate.
     */
    public void setEstDeliveryDays(final Number estDeliveryDays) {
        this.estDeliveryDays = estDeliveryDays;
    }

    /**
     * Get the ID of the shipment of this Rate.
     *
     * @return ID of the shipment of this Rate.
     */
    public String getShipmentId() {
        return shipmentId;
    }

    /**
     * Set the ID of the shipment of this Rate.
     *
     * @param shipmentId ID of the shipment of this Rate.
     */
    public void setShipmentId(final String shipmentId) {
        this.shipmentId = shipmentId;
    }

    /**
     * Get the ID of the carrier account of this Rate.
     *
     * @return ID of the carrier account of this Rate.
     */
    public String getCarrierAccountId() {
        return carrierAccountId;
    }

    /**
     * Set the ID of the carrier account of this Rate.
     *
     * @param carrierAccountId ID of the carrier account of this Rate.
     */
    public void setCarrierAccountId(final String carrierAccountId) {
        this.carrierAccountId = carrierAccountId;
    }

    /**
     * Get the TimeInTransit of this Rate.
     *
     * @return TimeInTransit object.
     */
    public TimeInTransit getTimeInTransit() {
        return timeInTransit;
    }

    /**
     * Set the TimeInTransit of this Rate.
     *
     * @param timeInTransit TimeInTransit object.
     */
    public void setTimeInTransit(final TimeInTransit timeInTransit) {
        this.timeInTransit = timeInTransit;
    }

    /**
     * Create a new Rate object.
     *
     * @param id                     ID of this Rate.
     * @param carrier                The carrier of this Rate.
     * @param service                The service of this Rate.
     * @param rate                   The rate of this Rate.
     * @param currency               The currency of this Rate.
     * @param listRate               The list rate of this Rate.
     * @param listCurrency           The list currency of this Rate.
     * @param retailRate             The retail rate of this Rate.
     * @param retailCurrency         The retail currency of this Rate.
     * @param deliveryDays           The delivery days of this Rate.
     * @param deliveryDate           The delivery date of this Rate.
     * @param deliveryDateGuaranteed true if the delivery date is guaranteed for this Rate.
     * @param estDeliveryDays        The estimated delivery days for this Rate.
     * @param shipmentId             ID of the shipment of this Rate.
     * @param carrierAccountId       ID of the carrier account of this Rate.
     * @param timeInTransit          TimeInTransit object.
     */
    public Rate(String id, String carrier, String service, Float rate, String currency, Float listRate,
                String listCurrency, Float retailRate, String retailCurrency, Number deliveryDays, String deliveryDate,
                Boolean deliveryDateGuaranteed, Number estDeliveryDays, String shipmentId, String carrierAccountId,
                TimeInTransit timeInTransit) {
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

    /**
     * Retrieve a Rate from the API.
     *
     * @param id ID of the Rate to retrieve.
     * @return Rate object.
     * @throws EasyPostException
     */
    public static Rate retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Rate from the API.
     *
     * @param id     ID of the Rate to retrieve.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Rate object.
     * @throws EasyPostException
     */
    public static Rate retrieve(final String id, final String apiKey) throws EasyPostException {
        Rate response;
        response = request(RequestMethod.GET, instanceURL(Rate.class, id), null, Rate.class, apiKey);

        return response;
    }
}
