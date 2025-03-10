package com.easypost.service;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Brand;
import com.easypost.model.ChildUserCollection;
import com.easypost.model.User;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
        String endpoint = "users/" + id;

        return Requestor.request(RequestMethod.GET, endpoint, null, User.class, client);
    }

    /**
     * Retrieve your User from the API.
     *
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public User retrieveMe() throws EasyPostException {
        String endpoint = "users";

        return Requestor.request(RequestMethod.GET, endpoint, null, User.class, client);
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

        String endpoint = "users";

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, User.class, client);
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

        String endpoint = "users/" + id;

        return Requestor.request(RequestMethod.PUT, endpoint, wrappedParams, User.class, client);
    }

    /**
     * Delete this User.
     *
     * @param id The ID of the user.
     * @throws EasyPostException when the request fails.
     */
    public void delete(final String id) throws EasyPostException {
        String endpoint = "users/" + id;

        Requestor.request(RequestMethod.DELETE, endpoint, null, User.class, client);
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
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("brand", params);

        String endpoint = "users/" + id + "/brand";

        return Requestor.request(RequestMethod.PUT, endpoint, wrappedParams, Brand.class, client);
    }

    /**
     * Retrieve the paginated list of child users for the authenticated user.
     *
     * @param params Map of parameters.
     * @return ChildUserCollection object.
     * @throws EasyPostException when the request fails.
     */
    public ChildUserCollection allChildren(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "users/children";
        
        return Requestor.request(RequestMethod.GET, endpoint, params, ChildUserCollection.class, client);
    }

    /**
     * Get the next page of a ChildUserCollection.
     *
     * @param collection ChildUserCollection to get next page of.
     * @param pageSize   The number of results to return on the next page.
     * @return ChildUserCollection object.
     * @throws EndOfPaginationError when there are no more pages to retrieve.
     */
    public ChildUserCollection getNextPage(ChildUserCollection collection, Integer pageSize)
        throws EndOfPaginationError {
        return collection.getNextPage(new Function<Map<String, Object>, ChildUserCollection>() {
            @Override @SneakyThrows
            public ChildUserCollection apply(Map<String, Object> parameters) {
                return allChildren(parameters);
            }
        }, collection.getChildren(), pageSize);
    }
}
