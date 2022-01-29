package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class User extends EasyPostResource {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String balance;
    private String rechargeAmount;
    private String secondaryRechargeAmount;
    private String rechargeThreshold;
    private List<User> children;

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
        return request(RequestMethod.GET, instanceURL(User.class, id), null, User.class, apiKey);
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
        return request(RequestMethod.GET, classURL(User.class), null, User.class, apiKey);
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
     * @return User object.
     * @throws EasyPostException when the request fails.
     */
    public static User create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
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

        return request(RequestMethod.POST, classURL(User.class), wrappedParams, User.class, apiKey, false);
    }

    /**
     * Get the ID of the User.
     *
     * @return the ID of the User.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the User.
     *
     * @param id the ID of the User.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the name of the User.
     *
     * @return the name of the User.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the User.
     *
     * @param name the name of the User.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the email of the User.
     *
     * @return the email of the User.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the User.
     *
     * @param email the email of the User.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Get the phone number of the User.
     *
     * @return the phone number of the User.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phone number of the User.
     *
     * @param phoneNumber the phone number of the User.
     */
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the balance of the User.
     *
     * @return the balance of the User.
     */
    public String getBalance() {
        return balance;
    }

    /**
     * Set the balance of the User.
     *
     * @param balance the balance of the User.
     */
    public void setBalance(final String balance) {
        this.balance = balance;
    }

    /**
     * Get the recharge amount of the User.
     *
     * @return the recharge amount of the User.
     */
    public String getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     * Set the recharge amount of the User.
     *
     * @param rechargeAmount the recharge amount of the User.
     */
    public void setRechargeAmount(final String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     * Get the secondary recharge amount of the User.
     *
     * @return the secondary recharge amount of the User.
     */
    public String getSecondaryRechargeAmount() {
        return secondaryRechargeAmount;
    }

    /**
     * Set the secondary recharge amount of the User.
     *
     * @param secondaryRechargeAmount the secondary recharge amount of the User.
     */
    public void setSecondaryRechargeAmount(final String secondaryRechargeAmount) {
        this.secondaryRechargeAmount = secondaryRechargeAmount;
    }

    /**
     * Get the recharge threshold of the User.
     *
     * @return the recharge threshold of the User.
     */
    public String getRechargeThreshold() {
        return rechargeThreshold;
    }

    /**
     * Set the recharge threshold of the User.
     *
     * @param rechargeThreshold the recharge threshold of the User.
     */
    public void setRechargeThreshold(final String rechargeThreshold) {
        this.rechargeThreshold = rechargeThreshold;
    }

    /**
     * Get the children of the User.
     *
     * @return List of User objects.
     */
    public List<User> getChildren() {
        return children;
    }

    /**
     * Set the children of the User.
     *
     * @param children List of User objects.
     */
    public void setChildren(final List<User> children) {
        this.children = children;
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

        User response =
                request(RequestMethod.PUT, instanceURL(User.class, this.getId()), wrappedParams, User.class, apiKey);

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
        request(RequestMethod.DELETE, instanceURL(User.class, this.getId()), null, User.class, apiKey);
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

        throw new EasyPostException(
                String.format("Unable to find api key. Please contact %s.", EasyPostResource.EASYPOST_SUPPORT_EMAIL));
    }
}

