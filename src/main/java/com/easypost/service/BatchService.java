package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Batch;
import com.easypost.model.BatchCollection;
import com.easypost.model.Shipment;

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

        String endpoint = "batches";

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, Batch.class, client);
    }

    /**
     * Retrieve a Batch object from the API.
     *
     * @param id ID of the Batch to retrieve.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch retrieve(final String id) throws EasyPostException {
        String endpoint = "batches/" + id;

        return Requestor.request(RequestMethod.GET, endpoint, null, Batch.class, client);
    }

    /**
     * List all Batch objects.
     *
     * @param params Map of parameters.
     * @return BatchCollection object.
     * @throws EasyPostException when the request fails.
     */
    public BatchCollection all(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "batches";

        return Requestor.request(RequestMethod.GET, endpoint, params, BatchCollection.class, client);
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

        String endpoint = "batches";

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, Batch.class, client);
    }

    /**
     * Label this Batch object.
     *
     * @param id     The ID of batch.
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch label(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "batches/" + id + "/label";

        return Requestor.request(RequestMethod.POST, endpoint, params, Batch.class, client);
    }

    /**
     * Add shipments to this Batch object.
     *
     * @param id     The ID of batch.
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch addShipments(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "batches/" + id + "/add_shipments";

        return Requestor.request(RequestMethod.POST, endpoint, params, Batch.class, client);
    }

    /**
     * Add shipments to this Batch object.
     *
     * @param id        The ID of batch.
     * @param shipments List of Shipment objects.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch addShipments(final String id, final List<Shipment> shipments) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);

        return this.addShipments(id, params);
    }

    /**
     * Remove shipments from this Batch object.
     *
     * @param id     The ID of batch.
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch removeShipments(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "batches/" + id + "/remove_shipments";

        return Requestor.request(RequestMethod.POST, endpoint, params, Batch.class,
                client);
    }

    /**
     * Remove shipments from this Batch object.
     *
     * @param id        The ID of batch.
     * @param shipments List of Shipment objects.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch removeShipments(final String id, final List<Shipment> shipments) throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("shipments", shipments);

        return this.removeShipments(id, params);
    }

    /**
     * Buy this batch.
     *
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch buy(final String id) throws EasyPostException {
        return this.buy(id, null);
    }

    /**
     * Buy this batch.
     *
     * @param id     The ID of batch.
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch buy(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "batches/" + id + "/buy";

        return Requestor.request(RequestMethod.POST, endpoint, params, Batch.class, client);
    }

    /**
     * Create a scan form for this batch.
     *
     * @param id The ID of batch.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch createScanForm(final String id) throws EasyPostException {
        return this.createScanForm(id, null);
    }

    /**
     * Create a scan form for this batch.
     *
     * @param id     The ID of batch.
     * @param params Map of parameters.
     * @return Batch object.
     * @throws EasyPostException when the request fails.
     */
    public Batch createScanForm(final String id, final Map<String, Object> params) throws EasyPostException {
        String endpoint = "batches/" + id + "/scan_form";

        return Requestor.request(RequestMethod.POST, endpoint, params, Batch.class, client);
    }
}
