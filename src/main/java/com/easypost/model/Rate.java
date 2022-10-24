package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;

public class Rate extends EasyPostResource {
    private String carrier;
    private String service;
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
    private String billingType;
    private CarbonOffset carbonOffset;

    /**
     * Get billing type of this rate.
     *
     * @return billing type of this rate.
     */
    public String getBillingType() {
        return billingType;
    }

    /**
     * Set billing type of this rate.
     *
     * @param billingType billing type of this rate.
     */
    public void setBillingType(final String billingType) {
        this.billingType = billingType;
    }

    /**
     * Get the carbon offset of this Rate.
     *
     * @return Carbon offset of this Rate.
     */
    public CarbonOffset getCarbonOffset() {
        return carbonOffset;
    }

    /**
     * Set the carbon offset of this Rate.
     *
     * @param carbonOffset Carbon offset of this Rate.
     */
    public void setCarbonOffset(final CarbonOffset carbonOffset) {
        this.carbonOffset = carbonOffset;
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
     * Get whether the delivery date is guaranteed for this Rate.
     *
     * @return true if the delivery date is guaranteed for this Rate.
     */
    public Boolean getDeliveryDateGuaranteed() {
        return deliveryDateGuaranteed;
    }

    /**
     * Set whether the delivery date is guaranteed for this Rate.
     *
     * @param deliveryDateGuaranteed true if the delivery date is guaranteed for this Rate.
     */
    public void setDeliveryDateGuaranteed(final Boolean deliveryDateGuaranteed) {
        this.deliveryDateGuaranteed = deliveryDateGuaranteed;
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
     * Retrieve a Rate from the API.
     *
     * @param id ID of the Rate to retrieve.
     * @return Rate object.
     * @throws EasyPostException when the request fails.
     */
    public static Rate retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Rate from the API.
     *
     * @param id     ID of the Rate to retrieve.
     * @param apiKey API key to use in request (overrides default API key).
     * @return Rate object.
     * @throws EasyPostException when the request fails.
     */
    public static Rate retrieve(final String id, final String apiKey) throws EasyPostException {
        Rate response;
        response = Requestor.request(RequestMethod.GET, instanceURL(Rate.class, id), null, Rate.class, apiKey);

        return response;
    }
}
