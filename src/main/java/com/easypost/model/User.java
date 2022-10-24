package com.easypost.model;

import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.FilteringError;
import com.easypost.net.Requestor;
import com.easypost.net.Requestor.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class User extends BaseUser {
    /**
     * Retrieve a User from the API.
     *
     * @param id The ID of the User to retrieve.
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public static User retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a User from the API.
     *
     * @param id     The ID of the User to retrieve.
     * @param apiKey API key to use in request (overrides default API key).
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public static User retrieve(final String id, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, instanceURL(User.class, id), null, User.class, apiKey);
    }

    /**
     * Retrieve your User from the API.
     *
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public static User retrieveMe() throws EasyPostException {
        return retrieveMe(null);
    }

    /**
     * Retrieve your User from the API.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public static User retrieveMe(final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, classURL(User.class), null, User.class, apiKey);
    }

    /**
     * Create a User object with a map of parameters.
     *
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public static User create() throws EasyPostException {
        return create(null, null);
    }

    /**
     * Create a User object with a map of parameters.
     *
     * @param params map of User parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public static User create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("user", params);

        return Requestor.request(RequestMethod.POST, classURL(User.class), wrappedParams, User.class, apiKey);
    }

    /**
     * Create a User object with a map of parameters.
     *
     * @param params map of User parameters.
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public static User create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Update this User.
     *
     * @param params map of User parameters.
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public User update(final Map<String, Object> params) throws EasyPostException {
        return update(params, null);
    }

    /**
     * Update this User.
     *
     * @param params map of User parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public User update(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("user", params);

        User response = Requestor.request(RequestMethod.PUT,
            instanceURL(User.class, this.getId()), wrappedParams, User.class, apiKey);

        this.merge(this, response);
        return this;
    }

    /**
     * Delete this User.
     *
     * @throws EasyPostException when the request fails.
     */
    public void delete() throws EasyPostException {
        this.delete(null);
    }

    /**
     * Delete this User.
     *
     * @param apiKey API key to use in request (overrides default API key).
     * @throws EasyPostException when the request fails.
     */
    public void delete(final String apiKey) throws EasyPostException {
        Requestor.request(RequestMethod.DELETE, instanceURL(User.class, this.getId()), null, User.class, apiKey);
    }

    /**
     * Get this User's API keys.
     *
     * @return List of ApiKey objects.
     * @throws EasyPostException when the request fails.
     */
    public List<ApiKey> apiKeys() throws EasyPostException {
        ApiKeys parentKeys = ApiKeys.all();

        if (Objects.equals(this.getId(), parentKeys.getId())) {
            return parentKeys.getKeys();
        }

        for (int i = 0; i < parentKeys.getChildren().size(); i++) {
            if (this.getId().equals(parentKeys.getChildren().get(i).getId())) {
                return parentKeys.getChildren().get(i).getKeys();
            }
        }
        
        throw new FilteringError(String.format(Constants.NO_OBJECT_FOUND, "API keys"));
    }

    /**
     * Update the user brand.
     *
     * @param params Map of parameters.
     * @return Brand object.
     * @throws EasyPostException when the request fails.
     */
    public Brand updateBrand(Map<String, Object> params) throws EasyPostException {
        return updateBrand(params, null);
    }

    /**
     * Update the user brand.
     *
     * @param params Map of parameters.
     * @param apiKey User API key.
     * @return Brand object.
     * @throws EasyPostException when the request fails.
     */
    public Brand updateBrand(Map<String, Object> params, String apiKey) throws EasyPostException {
        String updateBrandUrl = String.format("%s/brand", instanceURL(User.class, this.getId()));
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("brand", params);
        return Requestor.request(RequestMethod.PUT, updateBrandUrl, wrappedParams, Brand.class, apiKey);
    }
}
