package com.easypost.service;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.FilteringError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.ApiKey;
import com.easypost.model.ApiKeys;

import java.util.List;
import java.util.Objects;

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
        String endpoint = "api_keys";

        return Requestor.request(RequestMethod.GET, endpoint, null, ApiKeys.class, client);
    }

    /**
     * Get this User's API keys.
     *
     * @param id The ID of the user.
     * @return List of ApiKey objects.
     * @throws EasyPostException when the request fails.
     */
    public List<ApiKey> retrieveApiKeysForUser(final String id) throws EasyPostException {
        ApiKeys parentKeys = all();

        if (Objects.equals(id, parentKeys.getId())) {
            return parentKeys.getKeys();
        }

        for (int i = 0; i < parentKeys.getChildren().size(); i++) {
            if (id.equals(parentKeys.getChildren().get(i).getId())) {
                return parentKeys.getChildren().get(i).getKeys();
            }
        }

        throw new FilteringError(String.format(Constants.ErrorMessages.NO_OBJECT_FOUND, "API keys"));
    }
}
