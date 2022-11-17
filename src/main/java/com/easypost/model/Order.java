package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.utils.Utilities;

import lombok.Getter;

@Getter
public final class Order extends EasyPostResource {
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
     * Get the lowest rate for this Order.
     *
     * @return Lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate() throws EasyPostException {
        return this.lowestRate(null, null);
    }

    /**
     * Get the lowest rate for this Order.
     *
     * @param carriers The carriers to use in the filter.
     * @param services The services to use in the filter.
     * @return Lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final List<String> carriers, final List<String> services)
            throws EasyPostException {
        return Utilities.getLowestObjectRate(this.getRates(), carriers, services);
    }

    /**
     * Get the lowest rate for this order.
     *
     * @param carriers The carriers to use in the query.
     * @return Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final List<String> carriers) throws EasyPostException {
        return this.lowestRate(carriers, null);
    }
}
