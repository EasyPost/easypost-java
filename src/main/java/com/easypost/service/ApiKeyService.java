package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.ApiKeys;

public class ApiKeyService {
    private final EasyPostClient client;

    /**
     * ApiKeyService constructor.
     *
     * @param client The client object.
     */
    ApiKeyService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Get all API keys.
     *
     * @return ApiKeys object.
     * @throws EasyPostException when the request fails.
     */
    public ApiKeys all() throws EasyPostException {
        return Requestor.request(RequestMethod.GET, "api_keys", null, ApiKeys.class, client);
    }
}
