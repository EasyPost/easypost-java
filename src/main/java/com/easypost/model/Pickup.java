package com.easypost.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.utils.Utilities;

import lombok.Getter;

@Getter
public final class Pickup extends EasyPostResource {
    private String status;
    private String reference;
    private Date minDatetime;
    private Date maxDatetime;
    private Boolean isAccountAddress;
    private String instructions;
    private List<ShipmentMessage> messages;
    private String confirmation;
    private Address address;
    private List<CarrierAccount> carrierAccounts;
    private List<PickupRate> pickupRates;

    /**
     * Get the lowest rate for this Pickup.
     *
     * @return lowest PickupRate object
     * @throws EasyPostException when the request fails.
     */
    public PickupRate lowestRate() throws EasyPostException {
        return this.lowestRate(null, null);
    }

    /**
     * Get the lowest rate for this Pickup.
     *
     * @param carriers The carriers to use in the filter.
     * @param services The services to use in the filter.
     * @return lowest PickupRate object
     * @throws EasyPostException when the request fails.
     */
    public PickupRate lowestRate(final List<String> carriers, final List<String> services)
            throws EasyPostException {
        List<Rate> rates = new ArrayList<Rate>();

        for (PickupRate rate : this.getPickupRates()) {
            rates.add((Rate) rate);
        }

        return (PickupRate) Utilities.getLowestObjectRate(rates, carriers, services);
    }

    /**
     * Get the lowest rate for this pickup.
     *
     * @param carriers The carriers to use in the query.
     * @return PickupRate object
     * @throws EasyPostException when the request fails.
     */
    public PickupRate lowestRate(final List<String> carriers) throws EasyPostException {
        return this.lowestRate(carriers, null);
    }
}
