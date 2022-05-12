package com.easypost.model.beta;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EndShipper extends EasyPostResource {
    private String id;
    private String name;
    private String company;
    private String street1;
    private String street2;
    private String zip;
    private String state;
    private String country;
    private String phone;
    private String email;

    /**
     * Create EndShipper object from parameter map.
     *
     * @param params Map of end shipper parameters.
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public static EndShipper create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create EndShipper object from parameter map.
     *
     * @param params Map of end shipper parameters.
     * @param apiKey Optional API key to use for this request.
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public static EndShipper create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();

        wrappedParams.put("address", params);

        return request(RequestMethod.POST, String.format("%s/%s", EasyPost.BETA_API_BASE, "end_shippers"),
            wrappedParams, EndShipper.class, apiKey);
    }

    /**
     * Retrieve EndShipper object from API.
     *
     * @param id     ID of end shipper to retrieve.
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public static EndShipper retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve EndShipper object from API.
     *
     * @param id     ID of end shipper to retrieve.
     * @param apiKey API key to use in request (overrides default API key).
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public static EndShipper retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, String.format("%s/%s/%s",
            EasyPost.BETA_API_BASE, "end_shippers", id), null, EndShipper.class, apiKey);
    }

    /**
     * List all EndShipper objects.
     *
     * @param params Map of parameters.
     * @return List<EndShipper>.
     * @throws EasyPostException when the request fails.
     */
    public static List<EndShipper> all(final Map<String, Object> params)
            throws EasyPostException {
        return all(params, null);
    }

    /**
     * List all EndShipper objects.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return List<EndShipper>.
     * @throws EasyPostException when the request fails.
     */
    public static List<EndShipper> all(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        EndShipper[] response = request(RequestMethod.GET, String.format("%s/%s", EasyPost.BETA_API_BASE,
            "end_shippers"), params, EndShipper[].class, apiKey);

        return Arrays.asList(response);
    }

    /**
     * Update an EndShipper object.
     *
     * @param params Map of parameters.
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public EndShipper update(final Map<String, Object> params) throws EasyPostException {
        return update(params, null);
    }

    /**
     * Update an EndShipper object.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (overrides default API key).
     * @return EndShipper object.
     * @throws EasyPostException when the request fails.
     */
    public EndShipper update(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();

        wrappedParams.put("address", params);

        EndShipper response = request(RequestMethod.PUT, String.format("%s/%s/%s", EasyPost.BETA_API_BASE,
            "end_shippers", this.getId()), wrappedParams, EndShipper.class, apiKey);

        this.merge(this, response);
        return this;
    }

    /**
     * Get EndShipper ID.
     *
     * @return EndShipper ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set EndShipper ID.
     *
     * @param id EndShipper ID
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get name of EndShipper.
     *
     * @return EndShipper name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of EndShipper.
     *
     * @param name EndShipper name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get company of EndShipper.
     *
     * @return EndShipper company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set company of EndShipper.
     *
     * @param company EndShipper company
     */
    public void setCompany(final String company) {
        this.company = company;
    }

    /**
     * Get first line of EndShipper street.
     *
     * @return first line of EndShipper street
     */
    public String getStreet1() {
        return street1;
    }

    /**
     * Set first line of EndShipper street.
     *
     * @param street1 first line of EndShipper street
     */
    public void setStreet1(final String street1) {
        this.street1 = street1;
    }

    /**
     * Get second line of EndShipper street.
     *
     * @return second line of EndShipper street
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * Set second line of EndShipper street.
     *
     * @param street2 second line of EndShipper street
     */
    public void setStreet2(final String street2) {
        this.street2 = street2;
    }

    /**
     * Get zip code of EndShipper.
     *
     * @return EndShipper zip code
     */
    public String getZip() {
        return zip;
    }

    /**
     * Set zip code of EndShipper.
     *
     * @param zip EndShipper zip code
     */
    public void setZip(final String zip) {
        this.zip = zip;
    }

    /**
     * Get state of EndShipper.
     *
     * @return EndShipper state
     */
    public String getState() {
        return state;
    }

    /**
     * Set state of EndShipper.
     *
     * @param state EndShipper state
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * Get country of EndShipper.
     *
     * @return EndShipper country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set country of EndShipper.
     *
     * @param country EndShipper country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Get phone number of EndShipper.
     *
     * @return EndShipper phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set phone number of EndShipper.
     *
     * @param phone EndShipper phone number
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * Get email of EndShipper.
     *
     * @return EndShipper email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email of EndShipper.
     *
     * @param email EndShipper email
     */
    public void setEmail(final String email) {
        this.email = email;
    }
}
