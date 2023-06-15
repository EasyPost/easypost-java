package com.easypost.service;

import java.util.HashMap;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.CarrierMetadata;

public class CarrierMetadataService {
    private final EasyPostClient client;

    /**
     * CarrierMetadataService constructor.
     *
     * @param client The client object.
     */
    CarrierMetadataService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Retrieves all carrier metadata.
     *
     * @return CarrierMetadata object
     * @throws EasyPostException
     */
    public CarrierMetadata retrieve() throws EasyPostException {
        return retrieve(null);
    }

    /**
     * Retrieves carrier metadata for given carriers.
     *
     * @param carriers The list of carriers in string.
     * @return CarrierMetadata object
     * @throws EasyPostException
     */
    public CarrierMetadata retrieve(List<String> carriers) throws EasyPostException {
        return retrieve(carriers, null);
    }

    /**
     * Retrieves carrier metadata for given carriers and types.
     *
     * @param carriers The list of carriers in string.
     * @param types    The list of types in string.
     * @return CarrierMetadata object
     * @throws EasyPostException
     */
    public CarrierMetadata retrieve(List<String> carriers, List<String> types) throws EasyPostException {
        HashMap<String, Object> params = new HashMap<>();

        if (carriers != null && !carriers.isEmpty()) {
            params.put("carriers", String.join(",", carriers));
        }

        if (types != null && !types.isEmpty()) {
            params.put("types", String.join(",", types));
        }

        return this.client.request(RequestMethod.GET, "metadata/carriers", params,
                CarrierMetadata.class);
    }
}
