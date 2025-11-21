package com.easypost.service;

import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.EmbeddablesSession;

public class EmbeddableService {
    private final EasyPostClient client;

    /**
     * EmbeddableService constructor.
     *
     * @param client The client object.
     */
    EmbeddableService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create an EmbeddablesSession from the API.
     *
     * @param params Map of parameters.
     * @return EmbeddablesSession object
     * @throws EasyPostException when the request fails.
     */
    public EmbeddablesSession createSession(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "embeddables/session";

        return Requestor.request(RequestMethod.POST, endpoint, params, EmbeddablesSession.class, client);
    }
}
