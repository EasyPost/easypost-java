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
        return this.refresh(null, id);
    }

    /**
     * Refresh this Shipment.
     *
     * @param params The options for the query.
     * @param id     The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refresh(final Map<String, Object> params, final String id) throws EasyPostException {
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
        return this.newRates(new HashMap<String, Object>() {
        }, false, id);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param withCarbonOffset Whether to include a carbon offset when re-rating the
     *                         shipment.
     * @param id               The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final boolean withCarbonOffset, final String id) throws EasyPostException {
        return this.newRates(new HashMap<String, Object>() {
        }, withCarbonOffset, id);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param params The options for the query.
     * @param id     The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final Map<String, Object> params, final String id) throws EasyPostException {
        return this.newRates(params, false, id);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param params           The options for the query.
     * @param withCarbonOffset Whether to include a carbon offset when re-rating the
     *                         shipment.
     * @param id               The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final Map<String, Object> params, final boolean withCarbonOffset, final String id)
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
        return this.smartrates(params, id);
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param id The ID of shipment.
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Smartrate> smartrates(final String id) throws EasyPostException {
        return this.smartrates(null, id);
    }

    /**
     * Get Smartrates for this Shipment.
     *
     * @param params The options for the query.
     * @param id     The ID of shipment.
     * @return List of Smartrate objects
     * @throws EasyPostException when the request fails.
     */
    public List<Smartrate> smartrates(final Map<String, Object> params, final String id)
            throws EasyPostException {
        SmartrateCollection smartrateCollection = Requestor.request(RequestMethod.GET, String.format("%s/smartrate",
                Utilities.instanceURL(Shipment.class, id)), params, SmartrateCollection.class, client);

        return smartrateCollection.getSmartrates();
    }

    /**
     * Buy this Shipment.
     *
     * @param params The options for the query.
     * @param id     The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Map<String, Object> params, final String id) throws EasyPostException {
        return this.buy(params, false, null, id);
    }

    /**
     * Buy this Shipment.
     *
     * @param rate The Rate to use for this Shipment.
     * @param id   The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Rate rate, final String id) throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("rate", rate);

        return this.buy(params, false, null, id);
    }

    /**
     * Buy this Shipment.
     *
     * @param rate             The Rate to use for this Shipment.
     * @param withCarbonOffset Whether to include a carbon offset when buying the
     *                         shipment.
     * @param id               The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Rate rate, final boolean withCarbonOffset, final String id)
            throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("rate", rate);

        return this.buy(params, withCarbonOffset, null, id);
    }

    /**
     * Buy this Shipment.
     *
     * @param rate         The Rate to use for this Shipment.
     * @param endShipperId The id of the end shipper to use for this purchase.
     * @param id           The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Rate rate, final String endShipperId, final String id) throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("rate", rate);

        return this.buy(params, false, endShipperId, id);
    }

    /**
     * Buy this Shipment.
     *
     * @param params       The options for the query.
     * @param endShipperId The ID of the endshipper.
     * @param id           The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Map<String, Object> params, final String endShipperId, final String id)
            throws EasyPostException {
        return this.buy(params, false, endShipperId, id);
    }

    /**
     * Buy this Shipment.
     *
     * @param params           The options for the query.
     * @param withCarbonOffset Whether to include a carbon offset when buying the
     *                         shipment.
     * @param id               The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Map<String, Object> params, final boolean withCarbonOffset, final String id)
            throws EasyPostException {
        return this.buy(params, withCarbonOffset, null, id);
    }

    /**
     * Buy this Shipment.
     *
     * @param params           The options for the query.
     * @param withCarbonOffset Whether to include a carbon offset when buying the
     *                         shipment.
     * @param endShipperId     The id of the end shipper to use for this purchase.
     * @param id               The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final Map<String, Object> params, final boolean withCarbonOffset,
            final String endShipperId, final String id) throws EasyPostException {
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
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @param id               The ID of shipment.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     * @deprecated use {@link #lowestSmartRate(int, SmartrateAccuracy, Shipment)}
     *             instead.
     *             Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public Smartrate lowestSmartRate(int deliveryDay, String deliveryAccuracy, final String id)
            throws EasyPostException {
        return this.lowestSmartRate(deliveryDay, SmartrateAccuracy.getByKeyName(deliveryAccuracy), id);
    }

    /**
     * Get the lowest smartrate for this Shipment.
     *
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @param id               The ID of shipment.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     */
    public Smartrate lowestSmartRate(int deliveryDay, SmartrateAccuracy deliveryAccuracy, final String id)
            throws EasyPostException {
        List<Smartrate> smartrates = this.smartrates(null, id);

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
        return this.smartrates(null, id);
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
     * Get the lowest rate for this Shipment.
     *
     * @param shipment The shipment object.
     * @return lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final Shipment shipment) throws EasyPostException {
        return this.lowestRate(null, shipment);
    }

    /**
     * Get the lowest rate for this Shipment.
     *
     * @param carriers the carriers to use in the filter.
     * @param services the services to use in the filter.
     * @param shipment The shipment object.
     * @return lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final List<String> carriers, final List<String> services, final Shipment shipment)
            throws EasyPostException {
        return Utilities.getLowestObjectRate(shipment.getRates(), carriers, services);
    }

    /**
     * Get the lowest rate for this shipment.
     *
     * @param carriers the carriers to use in the query.
     * @param shipment The shipment object.
     * @return Rate object
     * @throws EasyPostException when the request fails.
     */
    public Rate lowestRate(final List<String> carriers, final Shipment shipment) throws EasyPostException {
        return this.lowestRate(carriers, null, shipment);
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
