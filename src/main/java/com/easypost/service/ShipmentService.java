package com.easypost.service;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.exception.General.FilteringError;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.ShipmentCollection;
import com.easypost.model.EstimatedDeliveryDate;
import com.easypost.model.EstimatedDeliveryDateResponse;
import com.easypost.model.Rate;
import com.easypost.model.Shipment;
import com.easypost.model.SmartRate;
import com.easypost.model.SmartrateAccuracy;
import com.easypost.model.SmartrateCollection;
import com.easypost.utils.InternalUtilities;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
    public Shipment create(final Map<String, Object> params, boolean withCarbonOffset) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("shipment", params);
        wrappedParams.put("carbon_offset", withCarbonOffset);

        String endpoint = "shipments";

        return this.client.request(RequestMethod.POST, endpoint, wrappedParams, Shipment.class);
    }

    /**
     * Retrieve a Shipment from the API.
     *
     * @param id The ID of the Shipment to retrieve.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment retrieve(final String id) throws EasyPostException {
        String endpoint = "shipments/" + id;

        return this.client.request(RequestMethod.GET, endpoint, null, Shipment.class);
    }

    /**
     * Get a list of all Shipment objects.
     *
     * @param params The options for the query.
     * @return ShipmentCollection object
     * @throws EasyPostException when the request fails.
     */
    public ShipmentCollection all(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "shipments";

        ShipmentCollection shipmentCollection = this.client.request(RequestMethod.GET, endpoint, params,
                ShipmentCollection.class);
        // we store the params in the collection so that we can use them to get the next page
        shipmentCollection.setPurchased(InternalUtilities.getOrDefault(params, "purchased", null));
        shipmentCollection.setIncludeChildren(InternalUtilities.getOrDefault(params, "include_children", null));

        return shipmentCollection;
    }

    /**
     * Get the next page of an ShipmentCollection.
     *
     * @param collection ShipmentCollection to get next page of.
     * @return ShipmentCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public ShipmentCollection getNextPage(ShipmentCollection collection) throws EndOfPaginationError {
        return getNextPage(collection, null);
    }

    /**
     * Get the next page of an ShipmentCollection.
     *
     * @param collection ShipmentCollection to get next page of.
     * @param pageSize   The number of results to return on the next page.
     * @return ShipmentCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public ShipmentCollection getNextPage(ShipmentCollection collection, Integer pageSize) throws EndOfPaginationError {
        return collection.getNextPage(new Function<Map<String, Object>, ShipmentCollection>() {
            @SneakyThrows
            public ShipmentCollection apply(Map<String, Object> parameters) {
                return all(parameters);
            }
        }, collection.getShipments(), pageSize);
    }

    /**
     * Get new rates for this Shipment.
     *
     * @param id The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final String id) throws EasyPostException {
        return this.newRates(id, new HashMap<String, Object>());
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
        return this.newRates(id, new HashMap<String, Object>() {}, withCarbonOffset);
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

        String endpoint = "shipments/" + id + "/rerate";

        return this.client.request(RequestMethod.POST, endpoint, params, Shipment.class);
    }

    /**
     * Get SmartRates for this Shipment.
     *
     * @param params The options for the query.
     * @param id     The ID of shipment.
     * @return List of SmartRate objects
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #smartrates(String, Map)} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public List<SmartRate> getSmartrates(final String id, final Map<String, Object> params) throws EasyPostException {
        return this.smartrates(id, params);
    }

    /**
     * Get SmartRate for this Shipment.
     *
     * @param id The ID of shipment.
     * @return List of SmartRate objects
     * @throws EasyPostException when the request fails.
     */
    public List<SmartRate> smartrates(final String id) throws EasyPostException {
        return this.smartrates(id, null);
    }

    /**
     * Get SmartRates for this Shipment.
     *
     * @param id     The ID of shipment.
     * @param params The options for the query.
     * @return List of SmartRate objects
     * @throws EasyPostException when the request fails.
     */
    public List<SmartRate> smartrates(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "shipments/" + id + "/smartrate";

        SmartrateCollection smartrateCollection = this.client.request(RequestMethod.GET, endpoint, params,
                SmartrateCollection.class);

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
        return this.buy(id, params, false);
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
    public Shipment buy(final String id, final Rate rate, final boolean withCarbonOffset) throws EasyPostException {
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

        String endpoint = "shipments/" + id + "/buy";

        return this.client.request(RequestMethod.POST, endpoint, params, Shipment.class);
    }

    /**
     * Refund this Shipment.
     *
     * @param id The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refund(final String id) throws EasyPostException {
        return this.refund(id, null);
    }

    /**
     * Refund this Shipment.
     *
     * @param id     The ID of shipment.
     * @param params The options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment refund(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "shipments/" + id + "/refund";

        return this.client.request(RequestMethod.POST, endpoint, params, Shipment.class);
    }

    /**
     * Label this Shipment.
     *
     * @param params The options for the query.
     * @param id     The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment label(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "shipments/" + id + "/label";

        return this.client.request(RequestMethod.GET, endpoint, params, Shipment.class);
    }

    /**
     * Insure this Shipment.
     *
     * @param params The options for the query.
     * @param id     The ID of shipment.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment insure(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "shipments/" + id + "/insure";

        return this.client.request(RequestMethod.POST, endpoint, params, Shipment.class);
    }

    /**
     * Get the lowest SmartRate for this Shipment.
     *
     * @param id               The ID of shipment.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @return lowest SmartRate object
     * @throws EasyPostException when the request fails.
     * @deprecated use {@link #lowestSmartRate(String, int, SmartrateAccuracy)}
     * instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public SmartRate lowestSmartRate(final String id, int deliveryDay, String deliveryAccuracy)
            throws EasyPostException {
        return this.lowestSmartRate(id, deliveryDay, SmartrateAccuracy.getByKeyName(deliveryAccuracy));
    }

    /**
     * Get the lowest SmartRate for this Shipment.
     *
     * @param id               The ID of shipment.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @return lowest SmartRate object
     * @throws EasyPostException when the request fails.
     */
    public SmartRate lowestSmartRate(final String id, final int deliveryDay, SmartrateAccuracy deliveryAccuracy)
            throws EasyPostException {
        List<SmartRate> smartrates = this.smartrates(id, null);

        SmartRate lowestSmartrate = findLowestSmartrate(smartrates, deliveryDay, deliveryAccuracy);

        return lowestSmartrate;
    }

    /**
     * Get SmartRates for this Shipment.
     *
     * @param id The ID of shipment.
     * @return List of SmartRate objects
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #smartrates(String, Map)} instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public List<SmartRate> getSmartrates(final String id) throws EasyPostException {
        return this.getSmartrates(id, null);
    }

    /**
     * Get the lowest SmartRate from a list of SmartRates.
     *
     * @param smartRates       List of SmartRates to filter from.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @return lowest SmartRate object
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #findLowestSmartrate(List, int, SmartrateAccuracy)}
     * instead.
     * Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public SmartRate getLowestSmartRate(final List<SmartRate> smartRates, int deliveryDay, String deliveryAccuracy)
            throws EasyPostException {
        return findLowestSmartrate(smartRates, deliveryDay, SmartrateAccuracy.getByKeyName(deliveryAccuracy));
    }

    /**
     * Find the lowest SmartRate from a list of SmartRates.
     *
     * @param smartRates       List of SmartRates to filter from.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     */
    public SmartRate findLowestSmartrate(final List<SmartRate> smartRates, int deliveryDay,
            SmartrateAccuracy deliveryAccuracy) throws EasyPostException {
        SmartRate lowestSmartrate = null;

        for (SmartRate rate : smartRates) {
            int smartrateDeliveryDay = rate.getTimeInTransit().getBySmartrateAccuracy(deliveryAccuracy);

            if (smartrateDeliveryDay > deliveryDay) {
                continue;
            } else if (lowestSmartrate == null || rate.getRate() < lowestSmartrate.getRate()) {
                lowestSmartrate = rate;
            }
        }

        if (lowestSmartrate == null) {
            throw new FilteringError(String.format(Constants.ErrorMessages.NO_OBJECT_FOUND, "rate"));
        }

        return lowestSmartrate;
    }

    /**
     * Generate a form for this shipment.
     *
     * @param id       The ID of shipment.
     * @param formType The form type for this shipment.
     * @return Return a shipment object.
     * @throws EasyPostException when the request fails.
     */
    public Shipment generateForm(final String id, final String formType) throws EasyPostException {
        return this.generateForm(id, formType, new HashMap<>());
    }

    /**
     * Generate a form for this shipment.
     *
     * @param id          The ID of shipment.
     * @param formType    The form type for this shipment.
     * @param formOptions The form options for this shipment.
     * @return Return a shipment object.
     * @throws EasyPostException when the request fails.
     */
    public Shipment generateForm(final String id, final String formType, final Map<String, Object> formOptions)
            throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        HashMap<String, Object> wrappedParams = new HashMap<>();

        params.put("type", formType);
        params.putAll(formOptions);
        wrappedParams.put("form", params);

        String endpoint = "shipments/" + id + "/forms";

        return this.client.request(RequestMethod.POST, endpoint, wrappedParams, Shipment.class);
    }

    /**
     * Retrieves the estimated delivery date of each Rate via SmartRate.
     * 
     * @param id              The id of the shipment.
     * @param plannedShipDate The planned shipment date.
     * @return EstimatedDeliveryDate object.
     * @throws EasyPostException
     */
    public List<EstimatedDeliveryDate> retrieveEstimatedDeliveryDate(final String id, final String plannedShipDate)
            throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("planned_ship_date", plannedShipDate);
        String endpoint = "shipments/" + id + "/smartrate/delivery_date";

        EstimatedDeliveryDateResponse response = this.client.request(RequestMethod.GET, endpoint, params,
                EstimatedDeliveryDateResponse.class);
        return response.getRates();
    }
}
