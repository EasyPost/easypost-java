package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.utils.Utilities;

import lombok.Getter;

@Getter
public class Shipment extends EasyPostResource {
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
    public Rate lowestRate(final List<String> carriers, final List<String> services)
            throws EasyPostException {
        return Utilities.getLowestObjectRate(this.getRates(), carriers, services);
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
}
