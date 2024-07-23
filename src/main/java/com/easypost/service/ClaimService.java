package com.easypost.service;

import java.util.Map;
import java.util.function.Function;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Claim;
import com.easypost.model.ClaimCollection;

import lombok.SneakyThrows;

public class ClaimService {
    private final EasyPostClient client;

    /**
     * ClaimService constructor.
     *
     * @param client The client object.
     */
    ClaimService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a new claim object from a map of parameters.
     *
     * @param params Map of parameters.
     * @return Claim object
     * @throws EasyPostException when the request fails.
     */
    public Claim create(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "claims";

        return Requestor.request(RequestMethod.POST, endpoint, params, Claim.class, client);
    }

    /**
     * Retrieve an Claim from the API.
     *
     * @param id The ID of the Claim to retrieve.
     * @return Claim object
     * @throws EasyPostException when the request fails.
     */
    public Claim retrieve(final String id) throws EasyPostException {
        String endpoint = "claims/" + id;

        return Requestor.request(RequestMethod.GET, endpoint, null, Claim.class, client);
    }

    /**
     * Get a list of Claims.
     *
     * @param params a map of parameters
     * @return ClaimCollection object
     * @throws EasyPostException when the request fails.
     */
    public ClaimCollection all(final Map<String, Object> params) throws EasyPostException {
        String type = (String) params.get("type");
        String status = (String) params.get("status");
        params.remove(type);
        params.remove(status);
        String endpoint = "claims";

        ClaimCollection claimCollection = 
            Requestor.request(RequestMethod.GET, endpoint, params, ClaimCollection.class, client);
        claimCollection.setType(type);
        claimCollection.setType(status);

        return claimCollection;
    }

    /**
     * Cancel an Claim from the API.
     *
     * @param id The ID of the Claim to cancel.
     * @return Claim object
     * @throws EasyPostException when the request fails.
     */
    public Claim cancel(final String id) throws EasyPostException {
        String endpoint = String.format("claims/%s/cancel", id);

        return Requestor.request(RequestMethod.POST, endpoint, null, Claim.class, client);
    }

    /**
     * Get the next page of an ClaimCollection.
     *
     * @param collection ClaimCollection to get next page of.
     * @return ClaimCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public ClaimCollection getNextPage(ClaimCollection collection) throws EndOfPaginationError {
        return getNextPage(collection, null);
    }

    /**
     * Get the next page of an ClaimCollection.
     *
     * @param collection ClaimCollection to get next page of.
     * @param pageSize   The number of results to return on the next page.
     * @return ClaimCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public ClaimCollection getNextPage(
            ClaimCollection collection, Integer pageSize) throws EndOfPaginationError {
        return collection.getNextPage(new Function<Map<String, Object>, ClaimCollection>() {
            @Override @SneakyThrows
            public ClaimCollection apply(Map<String, Object> parameters) {
                return all(parameters);
            }
        }, collection.getClaims(), pageSize);
    }
}
