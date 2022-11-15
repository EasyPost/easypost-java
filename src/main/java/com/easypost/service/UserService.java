package com.easypost.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.FilteringError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.ApiKey;
import com.easypost.model.ApiKeys;
import com.easypost.model.Brand;
import com.easypost.model.User;
import com.easypost.utils.Utilities;

public class UserService {
    private final EasyPostClient client;

    /**
     * UserService constructor.
     * 
     * @param client The client object.
     */
    UserService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Retrieve a User from the API.
     *
     * @param id The ID of the User to retrieve.
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public User retrieve(final String id) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.instanceURL(User.class, id), null, User.class, client);
    }

    /**
     * Retrieve your User from the API.
     *
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public User retrieveMe() throws EasyPostException {
        return Requestor.request(RequestMethod.GET, Utilities.classURL(User.class), null, User.class, client);
    }

    /**
     * Create a User object with a map of parameters.
     *
     * @param params Map of User parameters.
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public User create(final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("user", params);

        return Requestor.request(RequestMethod.POST, Utilities.classURL(User.class), wrappedParams, User.class, client);
    }

    /**
     * Update this User.
     *
     * @param id     The ID of user.
     * @param params Map of User parameters.
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public User update(final String id, final Map<String, Object> params) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("user", params);

        return Requestor.request(RequestMethod.PUT,
                Utilities.instanceURL(User.class, id), wrappedParams, User.class, client);
    }

    /**
     * Delete this User.
     *
     * @param id The ID of the user.
     * @throws EasyPostException when the request fails.
     */
    public void delete(final String id) throws EasyPostException {
        Requestor.request(RequestMethod.DELETE, Utilities.instanceURL(User.class, id),
                null, User.class, client);
    }

    /**
     * Get this User's API keys.
     *
     * @param id The ID of the user.
     * @return List of ApiKey objects.
     * @throws EasyPostException when the request fails.
     */
    public List<ApiKey> apiKeys(final String id) throws EasyPostException {
        ApiKeys parentKeys = client.apikeys.all();

        if (Objects.equals(id, parentKeys.getId())) {
            return parentKeys.getKeys();
        }

        for (int i = 0; i < parentKeys.getChildren().size(); i++) {
            if (id.equals(parentKeys.getChildren().get(i).getId())) {
                return parentKeys.getChildren().get(i).getKeys();
            }
        }

        throw new FilteringError(String.format(Constants.NO_OBJECT_FOUND, "API keys"));
    }

    /**
     * Update the user brand.
     *
     * @param id     The ID of user.
     * @param params Map of parameters.
     * @return Brand object.
     * @throws EasyPostException when the request fails.
     */
    public Brand updateBrand(final String id, final Map<String, Object> params) throws EasyPostException {
        String updateBrandUrl = String.format("%s/brand", Utilities.instanceURL(User.class, id));
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("brand", params);

        return Requestor.request(RequestMethod.PUT, updateBrandUrl, wrappedParams, Brand.class, client);
    }
}
