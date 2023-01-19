package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.ScanForm;
import com.easypost.model.ScanFormCollection;

import java.util.Map;

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
        return Requestor.request(RequestMethod.POST, "scan_forms", params, ScanForm.class, client);
    }

    /**
     * Retrieve a ScanForm from the API.
     *
     * @param id the id of the ScanForm to retrieve.
     * @return ScanForm object.
     * @throws EasyPostException when the request fails. when the request fails.
     */
    public ScanForm retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, "scan_forms/" + id, null, ScanForm.class, client);
    }

    /**
     * Get a list of ScanForms from the API.
     *
     * @param params the parameters to send to the API.
     * @return ScanFormCollection object.
     * @throws EasyPostException when the request fails. when the request fails.
     */
    public ScanFormCollection all(final Map<String, Object> params) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, "scan_forms", params, ScanFormCollection.class, client);
    }
}
