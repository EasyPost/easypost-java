package com.easypost.service;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.exception.General.FilteringError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.ShipmentCollection;
import com.easypost.model.EstimatedDeliveryDate;
import com.easypost.model.EstimatedDeliveryDateResponse;
import com.easypost.model.Rate;
import com.easypost.model.RecommendShipDateForShipmentResult;
import com.easypost.model.RecommendShipDateResponse;
import com.easypost.model.Shipment;
import com.easypost.model.SmartRate;
import com.easypost.model.SmartRateAccuracy;
import com.easypost.model.SmartRateCollection;
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
        Map<String, Object> wrappedParams = new HashMap<>();
        wrappedParams.put("shipment", params);

        String endpoint = "shipments";

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, Shipment.class, client);
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

        return Requestor.request(RequestMethod.GET, endpoint, null, Shipment.class, client);
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

        ShipmentCollection shipmentCollection = Requestor.request(RequestMethod.GET, endpoint, params,
                ShipmentCollection.class, client);
        // we store the params in the collection so that we can use them to get the next
        // page

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
            @Override
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
     * @param id     The ID of shipment.
     * @param params The options for the query.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment newRates(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "shipments/" + id + "/rerate";

        return Requestor.request(RequestMethod.POST, endpoint, params, Shipment.class, client);
    }

    /**
     * Get SmartRate for this Shipment.
     *
     * @param id The ID of shipment.
     * @return List of SmartRate objects
     * @throws EasyPostException when the request fails.
     */
    public List<SmartRate> smartRates(final String id) throws EasyPostException {
        return this.smartRates(id, null);
    }

    /**
     * Get SmartRates for this Shipment.
     *
     * @param id     The ID of shipment.
     * @param params The options for the query.
     * @return List of SmartRate objects
     * @throws EasyPostException when the request fails.
     */
    public List<SmartRate> smartRates(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "shipments/" + id + "/smartrate";

        SmartRateCollection smartRateCollection = Requestor.request(RequestMethod.GET, endpoint, params,
                SmartRateCollection.class, client);

        return smartRateCollection.getSmartRates();
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
        return this.buy(id, params, null);
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

        return this.buy(id, params, null);
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

        return this.buy(id, params, endShipperId);
    }

    /**
     * Buy this Shipment.
     *
     * @param id           The ID of shipment.
     * @param params       The options for the query.
     * @param endShipperId The id of the end shipper to use for this purchase.
     * @return Shipment object
     * @throws EasyPostException when the request fails.
     */
    public Shipment buy(final String id, final Map<String, Object> params, final String endShipperId)
            throws EasyPostException {
        if (endShipperId != null && !endShipperId.isEmpty()) {
            params.put("end_shipper_id", endShipperId);
        }

        String endpoint = "shipments/" + id + "/buy";

        return Requestor.request(RequestMethod.POST, endpoint, params, Shipment.class, client);
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

        return Requestor.request(RequestMethod.POST, endpoint, params, Shipment.class, client);
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

        return Requestor.request(RequestMethod.GET, endpoint, params, Shipment.class, client);
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

        return Requestor.request(RequestMethod.POST, endpoint, params, Shipment.class, client);
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
    public SmartRate lowestSmartRate(final String id, final int deliveryDay, SmartRateAccuracy deliveryAccuracy)
            throws EasyPostException {
        List<SmartRate> smartrates = this.smartRates(id, null);

        SmartRate lowestSmartRate = findLowestSmartRate(smartrates, deliveryDay, deliveryAccuracy);

        return lowestSmartRate;
    }

    /**
     * Find the lowest SmartRate from a list of SmartRates.
     *
     * @param smartRates       List of SmartRates to filter from.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @return lowest SmartRate object
     * @throws EasyPostException when the request fails.
     */
    public SmartRate findLowestSmartRate(final List<SmartRate> smartRates, int deliveryDay,
            SmartRateAccuracy deliveryAccuracy) throws EasyPostException {
        SmartRate lowestSmartRate = null;

        for (SmartRate rate : smartRates) {
            int smartrateDeliveryDay = rate.getTimeInTransit().getSmartRateAccuracy(deliveryAccuracy);

            if (smartrateDeliveryDay > deliveryDay) {
                continue;
            } else if (lowestSmartRate == null || rate.getRate() < lowestSmartRate.getRate()) {
                lowestSmartRate = rate;
            }
        }

        if (lowestSmartRate == null) {
            throw new FilteringError(String.format(Constants.ErrorMessages.NO_OBJECT_FOUND, "rate"));
        }

        return lowestSmartRate;
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

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, Shipment.class,
                client);
    }

    /**
     * Retrieves the estimated delivery date of each Rate via SmartRate.
     * 
     * @param id              The id of the shipment.
     * @param plannedShipDate The planned shipment date.
     * @return EstimatedDeliveryDate object.
     * @throws EasyPostException When the request fails.
     */
    public List<EstimatedDeliveryDate> retrieveEstimatedDeliveryDate(final String id, final String plannedShipDate)
            throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("planned_ship_date", plannedShipDate);
        String endpoint = "shipments/" + id + "/smartrate/delivery_date";

        EstimatedDeliveryDateResponse response = Requestor.request(RequestMethod.GET, endpoint, params,
                EstimatedDeliveryDateResponse.class, client);
        return response.getRates();
    }

    /**
     * Retrieve a recommended ship date for an existing Shipment via the Precision
     * Shipping API,
     * based on a specific desired delivery date.
     *
     * @param id                  The id of the shipment.
     * @param desiredDeliveryDate The desired delivery date.
     * @return EstimatedDeliveryDate object.
     * @throws EasyPostException When the request fails.
     */
    public List<RecommendShipDateForShipmentResult> recommendShipDate(final String id, final String desiredDeliveryDate)
            throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("desired_delivery_date", desiredDeliveryDate);
        String endpoint = "shipments/" + id + "/smartrate/precision_shipping";

        RecommendShipDateResponse response = Requestor.request(RequestMethod.GET, endpoint, params,
                RecommendShipDateResponse.class, client);
        return response.getRates();
    }
}
