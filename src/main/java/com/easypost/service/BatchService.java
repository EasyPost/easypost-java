package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Batch;
import com.easypost.model.BatchCollection;
import com.easypost.model.Shipment;
import com.easypost.utils.Utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchService {
    private final EasyPostClient client;

    /**
     * BatchService constructor.
     * 
     * @param client The client object.
     */
    BatchService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a Batch object.
     *
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch create() throws EasyPostException {
        return create(null);
    }

    /**
     * Create a Batch object.
     *
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("batch", params);

        return Requestor.request(RequestMethod.POST, Utilities.classURL(Batch.class), wrappedParams, Batch.class,
                client);
    }

    /**
     * Retrieve a Batch object from the API.
     *
     * @param id ID of the Batch to retrieve.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.instanceURL(Batch.class, id), null, Batch.class, client);
    }

    /**
     * List all Batch objects.
     *
     * @param params Map of parameters.
     * @return BatchCollection object.
     * @throws EasyPostException when the request fails.
     */
    public BatchCollection all(final Map<String, Object> params) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.classURL(Batch.class), params, BatchCollection.class,
                client);
    }

    /**
     * Create and buy a Batch object in one step.
     *
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch createAndBuy(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("batch", params);

        return Requestor.request(RequestMethod.POST, Utilities.classURL(Batch.class), wrappedParams, Batch.class,
                client);
    }

    /**
     * Refresh this Batch object.
     *
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch refresh(String id) throws EasyPostException {
        return this.refresh(null, id);
    }

    /**
     * Refresh this Batch object.
     *
     * @param params Map of parameters.
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch refresh(final Map<String, Object> params, String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET,
                String.format("%s", Utilities.instanceURL(Batch.class, id)), params,
                Batch.class, client);
    }

    /**
     * Label this Batch object.
     *
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch label(String id) throws EasyPostException {
        return this.label(null, id);
    }

    /**
     * Label this Batch object.
     *
     * @param params Map of parameters.
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch label(final Map<String, Object> params, String id) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/label",
                Utilities.instanceURL(Batch.class, id)), params, Batch.class, client);
    }

    /**
     * Add shipments to this Batch object.
     *
     * @param params Map of parameters.
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch addShipments(final Map<String, Object> params, String id) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/add_shipments",
                Utilities.instanceURL(Batch.class, id)), params, Batch.class, client);
    }

    /**
     * Add shipments to this Batch object.
     *
     * @param shipments List of Shipment objects.
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch addShipments(final List<Shipment> shipments, String id) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);

        return this.addShipments(params, id);
    }

    /**
     * Remove shipments from this Batch object.
     *
     * @param params Map of parameters.
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch removeShipments(final Map<String, Object> params, String id) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/remove_shipments",
                Utilities.instanceURL(Batch.class, id)), params, Batch.class, client);
    }

    /**
     * Remove shipments from this Batch object.
     *
     * @param shipments List of Shipment objects.
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch removeShipments(final List<Shipment> shipments, String id) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);

        return this.removeShipments(params, id);
    }

    /**
     * Buy this batch.
     *
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch buy(String id) throws EasyPostException {
        return this.buy(null, id);
    }

    /**
     * Buy this batch.
     *
     * @param params Map of parameters.
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch buy(final Map<String, Object> params, String id) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/buy",
                Utilities.instanceURL(Batch.class, id)), params, Batch.class, client);
    }

    /**
     * Create a scan form for this batch.
     *
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch createScanForm(String id) throws EasyPostException {
        return this.createScanForm(null, id);
    }

    /**
     * Create a scan form for this batch.
     *
     * @param params Map of parameters.
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch createScanForm(final Map<String, Object> params, String id) throws EasyPostException {
        return Requestor.request(RequestMethod.POST, String.format("%s/scan_form",
                Utilities.instanceURL(Batch.class, id)), params, Batch.class, client);
    }
}
