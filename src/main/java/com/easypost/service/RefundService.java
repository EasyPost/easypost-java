package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.RefundCollection;
import com.easypost.model.Refund;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class RefundService {
    private final EasyPostClient client;

    /**
     * RefundService constructor.
     *
     * @param client The client object.
     */
    RefundService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a Refund object from a map of parameters.
     *
     * @param params Map of parameters
     * @return Refund object
     * @throws EasyPostException when the request fails.
     */
    public List<Refund> create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("refund", params);

        String endpoint = "refunds";

        Refund[] response = this.client.request(RequestMethod.POST, endpoint, wrappedParams, Refund[].class);

        return Arrays.asList(response);
    }

    /**
     * Retrieve a Refund object from the API.
     *
     * @param id ID of refund to retrieve
     * @return Refund object
     * @throws EasyPostException when the request fails.
     */
    public Refund retrieve(final String id) throws EasyPostException {
        String endpoint = "refunds/" + id;

        return this.client.request(RequestMethod.GET, endpoint, null, Refund.class);
    }

    /**
     * List all Refunds objects.
     *
     * @param params Map of parameters
     * @return RefundCollection object
     * @throws EasyPostException when the request fails.
     */
    public RefundCollection all(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "refunds";

        return this.client.request(RequestMethod.GET, endpoint, params, RefundCollection.class);
    }

    /**
     * Get the next page of an RefundCollection.
     *
     * @param collection RefundCollection to get next page of.
     * @return RefundCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public RefundCollection getNextPage(RefundCollection collection) throws EndOfPaginationError {
        return getNextPage(collection, null);
    }

    /**
     * Get the next page of an RefundCollection.
     *
     * @param collection RefundCollection to get next page of.
     * @param pageSize   The number of results to return on the next page.
     * @return RefundCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public RefundCollection getNextPage(RefundCollection collection, Integer pageSize) throws EndOfPaginationError {
        return collection.getNextPage(new Function<Map<String, Object>, RefundCollection>() {
            @SneakyThrows
            public RefundCollection apply(Map<String, Object> parameters) {
                return all(parameters);
            }
        }, collection.getRefunds(), pageSize);
    }
}
