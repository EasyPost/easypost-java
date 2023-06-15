package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.ScanFormCollection;
import com.easypost.model.ScanForm;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.function.Function;

public class ScanformService {
    private final EasyPostClient client;

    /**
     * ScanformService constructor.
     *
     * @param client The client object.
     */
    ScanformService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a ScanForm from a map of parameters.
     *
     * @param params the map of parameters.
     * @return ScanForm object.
     * @throws EasyPostException when the request fails. when the request fails.
     */
    public ScanForm create(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "scan_forms";

        return this.client.request(RequestMethod.POST, endpoint, params, ScanForm.class);
    }

    /**
     * Retrieve a ScanForm from the API.
     *
     * @param id the id of the ScanForm to retrieve.
     * @return ScanForm object.
     * @throws EasyPostException when the request fails. when the request fails.
     */
    public ScanForm retrieve(final String id) throws EasyPostException {
        String endpoint = "scan_forms/" + id;

        return this.client.request(RequestMethod.GET, endpoint, null, ScanForm.class);
    }

    /**
     * Get a list of ScanForms from the API.
     *
     * @param params the parameters to send to the API.
     * @return ScanFormCollection object.
     * @throws EasyPostException when the request fails. when the request fails.
     */
    public ScanFormCollection all(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "scan_forms";

        return this.client.request(RequestMethod.GET, endpoint, params, ScanFormCollection.class);
    }

    /**
     * Get the next page of an ScanFormCollection.
     *
     * @param collection ScanFormCollection to get next page of.
     * @return ScanFormCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public ScanFormCollection getNextPage(ScanFormCollection collection) throws EndOfPaginationError {
        return getNextPage(collection, null);
    }

    /**
     * Get the next page of an ScanFormCollection.
     *
     * @param collection ScanFormCollection to get next page of.
     * @param pageSize   The number of results to return on the next page.
     * @return ScanFormCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public ScanFormCollection getNextPage(ScanFormCollection collection, Integer pageSize) throws EndOfPaginationError {
        return collection.getNextPage(new Function<Map<String, Object>, ScanFormCollection>() {
            @SneakyThrows
            public ScanFormCollection apply(Map<String, Object> parameters) {
                return all(parameters);
            }
        }, collection.getScanForms(), pageSize);
    }
}
