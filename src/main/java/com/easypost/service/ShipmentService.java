package com.easypost.service;

import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.FilteringError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Rate;
import com.easypost.model.Shipment;
import com.easypost.model.ShipmentCollection;
import com.easypost.model.Smartrate;
import com.easypost.model.SmartrateAccuracy;
import com.easypost.model.SmartrateCollection;
import com.easypost.utils.Utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShipmentService {
    private final EasyPostClient client;

    /**
     * ShipmentService constructor.
     * 
     * @param client The client object.
     */
    ShipmentService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a new Shipment object from a map of parameters.
     *
     * @param params The map of parameters.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment create(final Map<String, Object> params) throws EasyPostException {
        return this.create(params, false);
    }

    /**
     * Create a new Shipment object from a map of parameters.
     *
     * @param params           The map of parameters.
     * @param withCarbonOffset Whether to include a carbon offset when creating the
     *                         shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment create(final Map<String, Object> params, boolean withCarbonOffset)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("shipment", params);
        wrappedParams.put("carbon_offset", withCarbonOffset);

        return Requestor.request(RequestMethod.POST, Utilities.classURL(Shipment.class), wrappedParams, Shipment.class,
                client);
    }

    /**
     * Retrieve a Shipment from the API.
     *
     * @param id The ID of the Shipment to retrieve.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.instanceURL(Shipment.class, id), null, Shipment.class,
                client);
    }

    /**
     * Get a list of all Shipment objects.
     *
     * @param params The options for the query.
     * @return ShipmentCollection object
     * @throws EasyPostException when the request fails.
     */
    public ShipmentCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.classURL(Shipment.class), params,
                ShipmentCollection.class, client);
    }

    /**
     * Refresh this Shipment.
     *
     * @param id The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refresh(String id) throws EasyPostException {
        return this.refresh(id, null);
    }

    /**
     * Refresh this Shipment.
     *
     * @param id     The ID of shipment.
     * @param params The options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refresh(final String id, final Map<String, Object> params) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, String.format("%s",
                Utilities.instanceURL(Shipment.class, id)), params, Shipment.class, client);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param id The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final String id) throws EasyPostException {
        return this.newRates(id, new HashMap<String, Object>() {
        }, false);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param id               The ID of shipment.
     * @param withCarbonOffset Whether to include a carbon offset when re-rating the
     *                         shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final String id, final boolean withCarbonOffset) throws EasyPostException {
        return this.newRates(id, new HashMap<String, Object>() {
        }, withCarbonOffset);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param id     The ID of shipment.
     * @param params The options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final String id, final Map<String, Object> params) throws EasyPostException {
        return this.newRates(id, params, false);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param id               The ID of shipment.
     * @param params           The options for the query.
     * @param withCarbonOffset Whether to include a carbon offset when re-rating the
     *                         shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final String id, final Map<String, Object> params, final boolean withCarbonOffset)
            throws EasyPostException {
        params.put("carbon_offset", withCarbonOffset);
        return Requestor.request(RequestMethod.POST, String.format("%s/rerate",
                Utilities.instanceURL(Shipment.class, id)), params, Shipment.class, client);
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param params The options for the query.
     * @param id     The ID of shipment.
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #smartrates(Map, Shipment)} instead.
     *             Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public List<Smartrate> getSmartrates(final Map<String, Object> params, final String id)
            throws EasyPostException {
        return this.smartrates(id, params);
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param id The ID of shipment.
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Smartrate> smartrates(final String id) throws EasyPostException {
        return this.smartrates(id, null);
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param id     The ID of shipment.
     * @param params The options for the query.
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Smartrate> smartrates(final String id, final Map<String, Object> params)
            throws EasyPostException {
        SmartrateCollection smartrateCollection = Requestor.request(RequestMethod.GET, String.format("%s/smartrate",
                Utilities.instanceURL(Shipment.class, id)), params, SmartrateCollection.class, client);

        return smartrateCollection.getSmartrates();
    }

    /**
     * Buy this Shipment.
     *
     * @param id     The ID of shipment.
     * @param params The options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final String id, final Map<String, Object> params) throws EasyPostException {
        return this.buy(id, params, false, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param id   The ID of shipment.
     * @param rate The Rate to use for this Shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final String id, final Rate rate) throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("rate", rate);

        return this.buy(id, params, false, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param id               The ID of shipment.
     * @param rate             The Rate to use for this Shipment.
     * @param withCarbonOffset Whether to include a carbon offset when buying the
     *                         shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final String id, final Rate rate, final boolean withCarbonOffset)
            throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("rate", rate);

        return this.buy(id, params, withCarbonOffset, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param id           The ID of shipment.
     * @param rate         The Rate to use for this Shipment.
     * @param endShipperId The id of the end shipper to use for this purchase.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final String id, final Rate rate, final String endShipperId) throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("rate", rate);

        return this.buy(id, params, false, endShipperId);
    }

    /**
     * Buy this Shipment.
     *
     * @param id           The ID of shipment.
     * @param params       The options for the query.
     * @param endShipperId The ID of the endshipper.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final String id, final Map<String, Object> params, final String endShipperId)
            throws EasyPostException {
        return this.buy(id, params, false, endShipperId);
    }

    /**
     * Buy this Shipment.
     *
     * @param id               The ID of shipment.
     * @param params           The options for the query.
     * @param withCarbonOffset Whether to include a carbon offset when buying the
     *                         shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final String id, final Map<String, Object> params, final boolean withCarbonOffset)
            throws EasyPostException {
        return this.buy(id, params, withCarbonOffset, null);
    }

    /**
     * Buy this Shipment.
     *
     * @param id               The ID of shipment.
     * @param params           The options for the query.
     * @param withCarbonOffset Whether to include a carbon offset when buying the
     *                         shipment.
     * @param endShipperId     The id of the end shipper to use for this purchase.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final String id, final Map<String, Object> params, final boolean withCarbonOffset,
            final String endShipperId) throws EasyPostException {
        params.put("carbon_offset", withCarbonOffset);

        if (endShipperId != null && !endShipperId.isEmpty()) {
            params.put("end_shipper_id", endShipperId);
        }

        return Requestor.request(RequestMethod.POST, String.format("%s/buy",
                Utilities.instanceURL(Shipment.class, id)), params, Shipment.class, client);
    }

    /**
     * Refund this Shipment.
     *
     * @param id The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refund(final String id) throws EasyPostException {
        return this.refund(null, id);
    }

    /**
     * Refund this Shipment.
     *
     * @param params The options for the query.
     * @param id     The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refund(final Map<String, Object> params, final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/refund",
                Utilities.instanceURL(Shipment.class, id)), params, Shipment.class, client);
    }

    /**
     * Label this Shipment.
     *
     * @param id The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment label(final String id) throws EasyPostException {
        return this.label(null, id);
    }

    /**
     * Label this Shipment.
     *
     * @param params The options for the query.
     * @param id     The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment label(final Map<String, Object> params, final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, String.format("%s/label",
                Utilities.instanceURL(Shipment.class, id)), params, Shipment.class, client);
    }

    /**
     * Insure this Shipment.
     *
     * @param id The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment insure(final String id) throws EasyPostException {
        return this.insure(null, id);
    }

    /**
     * Insure this Shipment.
     *
     * @param params The options for the query.
     * @param id     The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment insure(final Map<String, Object> params, final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/insure",
                Utilities.instanceURL(Shipment.class, id)), params, Shipment.class, client);
    }

    /**
     * Get the lowest smartrate for this Shipment.
     *
     * @param id               The ID of shipment.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     * @deprecated use {@link #lowestSmartRate(int, SmartrateAccuracy, Shipment)}
     *             instead.
     *             Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public Smartrate lowestSmartRate(final String id, int deliveryDay, String deliveryAccuracy)
            throws EasyPostException {
        return this.lowestSmartRate(id, deliveryDay, SmartrateAccuracy.getByKeyName(deliveryAccuracy));
    }

    /**
     * Get the lowest smartrate for this Shipment.
     *
     * @param id               The ID of shipment.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     */
    public Smartrate lowestSmartRate(final String id, final int deliveryDay, SmartrateAccuracy deliveryAccuracy)
            throws EasyPostException {
        List<Smartrate> smartrates = this.smartrates(id, null);

        Smartrate lowestSmartrate = findLowestSmartrate(smartrates, deliveryDay, deliveryAccuracy);

        return lowestSmartrate;
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param id The ID of shipment.
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #smartrates(Shipment)} instead.
     *             Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public List<Smartrate> getSmartrates(final String id) throws EasyPostException {
        return this.smartrates(id, null);
    }

    /**
     * Get the lowest Smartrate from a list of Smartrates.
     *
     * @param smartrates       List of Smartrates to filter from.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #findLowestSmartrate(List, int, SmartrateAccuracy)}
     *             instead.
     *             Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public Smartrate getLowestSmartRate(final List<Smartrate> smartrates, int deliveryDay,
            String deliveryAccuracy) throws EasyPostException {
        return findLowestSmartrate(smartrates, deliveryDay, SmartrateAccuracy.getByKeyName(deliveryAccuracy));
    }

    /**
     * Find the lowest Smartrate from a list of Smartrates.
     *
     * @param smartrates       List of Smartrates to filter from.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     */
    public Smartrate findLowestSmartrate(final List<Smartrate> smartrates, int deliveryDay,
            SmartrateAccuracy deliveryAccuracy) throws EasyPostException {
        Smartrate lowestSmartrate = null;

        for (Smartrate rate : smartrates) {
            int smartrateDeliveryDay = rate.getTimeInTransit().getBySmartrateAccuracy(deliveryAccuracy);

            if (smartrateDeliveryDay > deliveryDay) {
                continue;
            } else if (lowestSmartrate == null || rate.getRate() < lowestSmartrate.getRate()) {
                lowestSmartrate = rate;
            }
        }

        if (lowestSmartrate == null) {
            throw new FilteringError(String.format(Constants.NO_OBJECT_FOUND, "rate"));
        }

        return lowestSmartrate;
    }

    /**
     * Generate a form for this shipment.
     *
     * @param formType The form type for this shipment.
     * @param id       The ID of shipment.
     * @return Return a shipment object.
     * @throws EasyPostException when the request fails.
     */
    public Shipment generateForm(final String formType, final String id) throws EasyPostException {
        return this.generateForm(formType, null, id);
    }

    /**
     * Generate a form for this shipment.
     *
     * @param formType    The form type for this shipment.
     * @param formOptions The form options for this shipment.
     * @param id          The ID of shipment.
     * @return Return a shipment object.
     * @throws EasyPostException when the request fails.
     */
    public Shipment generateForm(final String formType, final Map<String, Object> formOptions, final String id)
            throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        HashMap<String, Object> wrappedParams = new HashMap<>();

        params.put("type", formType);
        params.putAll(formOptions);
        wrappedParams.put("form", params);

        return Requestor.request(RequestMethod.POST, String.format("%s/forms",
                Utilities.instanceURL(Shipment.class, id)), wrappedParams, Shipment.class, client);
    }
}
