/**
 * Adddress.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Address extends EasyPostResource {

    private String id;
    private String mode;
    private String name;
    private String company;
    private String street1;
    private String street2;
    private String zip;
    private String city;
    private String state;
    private String country;
    private String phone;
    private String email;
    private String message;
    private String carrierFacility;
    private String federalTaxId;
    private Boolean residential;
    private Map<String, AddressVerification> verifications;

    /**
     * Get Address ID.
     *
     * @return Address ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set address ID.
     *
     * @param id address ID
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get address mode.
     *
     * @return address mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set address mode.
     *
     * @param mode address mode
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Get name of address.
     *
     * @return address name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of address.
     *
     * @param name address name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get company of address.
     *
     * @return address company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set company of address.
     *
     * @param company address company
     */
    public void setCompany(final String company) {
        this.company = company;
    }

    /**
     * Get first line of address street.
     *
     * @return first line of address street
     */
    public String getStreet1() {
        return street1;
    }

    /**
     * Set first line of address street.
     *
     * @param street1 first line of address street
     */
    public void setStreet1(final String street1) {
        this.street1 = street1;
    }

    /**
     * Get second line of address street.
     *
     * @return second line of address street
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * Set second line of address street.
     *
     * @param street2 second line of address street
     */
    public void setStreet2(final String street2) {
        this.street2 = street2;
    }

    /**
     * Get zip code of address.
     *
     * @return address zip code
     */
    public String getZip() {
        return zip;
    }

    /**
     * Set zip code of address.
     *
     * @param zip address zip code
     */
    public void setZip(final String zip) {
        this.zip = zip;
    }

    /**
     * Get city of address.
     *
     * @return address city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set city of address.
     *
     * @param city address city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Get state of address.
     *
     * @return address state
     */
    public String getState() {
        return state;
    }

    /**
     * Set state of address.
     *
     * @param state address state
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * Get country of address.
     *
     * @return address country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set country of address.
     *
     * @param country address country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Get phone number of address.
     *
     * @return address phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set phone number of address.
     *
     * @param phone address phone number
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * Get email of address.
     *
     * @return address email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email of address.
     *
     * @param email address email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Get address message.
     *
     * @return address message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set address message.
     *
     * @param message address message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Get carrier facility for address.
     *
     * @return address carrier facility
     */
    public String getCarrierFacility() {
        return carrierFacility;
    }

    /**
     * Set carrier facility for address.
     *
     * @param carrierFacility address carrier facility
     */
    public void setCarrierFacility(final String carrierFacility) {
        this.carrierFacility = carrierFacility;
    }

    /**
     * Get federal tax id of address.
     *
     * @return address federal tax id
     */
    public String getFederalTaxId() {
        return federalTaxId;
    }

    /**
     * Set federal tax id of address.
     *
     * @param federalTaxId address federal tax id
     */
    public void setFederalTaxId(final String federalTaxId) {
        this.federalTaxId = federalTaxId;
    }

    /**
     * Get whether address is residential.
     *
     * @return whether address is residential
     */
    public Boolean getResidential() {
        return residential;
    }

    /**
     * Set whether address is residential.
     *
     * @param residential whether address is residential
     */
    public void setResidential(final Boolean residential) {
        this.residential = residential;
    }

    /**
     * Get verifications for address.
     *
     * @return address verifications
     */
    public Map<String, AddressVerification> getVerifications() {
        return verifications;
    }

    /**
     * Set verifications for address.
     *
     * @param verifications address verifications
     */
    public void setVerifications(final Map<String, AddressVerification> verifications) {
        this.verifications = verifications;
    }

    /**
     * Create Address object from parameter map.
     *
     * @param params Map of address parameters.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create Address object from parameter map.
     *
     * @param params Map of address parameters.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        String url = classURL(Address.class);

        List<String> verifyList = (List<String>) params.remove("verify");
        List<String> verifyStrictList = (List<String>) params.remove("verify_strict");

        if ((verifyList != null && verifyList.size() >= 1) ||
                (verifyStrictList != null && verifyStrictList.size() >= 1)) {
            url += "?";

            if (verifyList != null && verifyList.size() >= 1) {
                for (String verification : verifyList) {
                    url += "verify[]=" + verification + "&";
                }
            }

            if (verifyStrictList != null && verifyStrictList.size() >= 1) {
                for (String strictVerification : verifyStrictList) {
                    url += "verify_strict[]=" + strictVerification + "&";
                }
            }
        }

        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("address", params);

        return request(RequestMethod.POST, url, wrappedParams, Address.class, apiKey);
    }

    /**
     * Retrieve Address object from API.
     *
     * @param id ID of address to retrieve.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve Address object from API.
     *
     * @param id     ID of address to retrieve.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Address.class, id), null, Address.class, apiKey);
    }

    /**
     * List all Address objects.
     *
     * @param params Map of parameters.
     * @return AddressCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static AddressCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * List all Address objects.
     *
     * @param params Map of parameters.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return AddressCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static AddressCollection all(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, classURL(Address.class), params, AddressCollection.class, apiKey);
    }

    /**
     * Create Address object from parameter map and immediately verify it.
     *
     * @param params Map of address parameters.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address createAndVerify(final Map<String, Object> params) throws EasyPostException {
        return createAndVerify(params, null);
    }

    /**
     * Create Address object from parameter map and immediately verify it.
     *
     * @param params Map of address parameters.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address createAndVerify(final Map<String, Object> params, final String apiKey)
            throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("address", params);

        AddressVerifyResponse response;
        response = request(RequestMethod.POST, String.format("%s/create_and_verify", classURL(Address.class)),
                wrappedParams, AddressVerifyResponse.class, apiKey);

        if (response.getMessage() != null) {
            response.getAddress().setMessage(response.getMessage());
        }
        return response.getAddress();
    }

    /**
     * Create Address object from parameter map
     * and immediately verify it with a specific carrier.
     *
     * @param params  Map of address parameters.
     * @param carrier Carrier to verify address with.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address createAndVerifyWithCarrier(final Map<String, Object> params, final String carrier)
            throws EasyPostException {
        return createAndVerifyWithCarrier(params, carrier, null);
    }

    /**
     * Create Address object from parameter map
     * and immediately verify it with a specific carrier.
     *
     * @param params  Map of address parameters.
     * @param carrier Carrier to verify address with.
     * @param apiKey  API key to use in request (ovverides default API key).
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public static Address createAndVerifyWithCarrier(final Map<String, Object> params, final String carrier,
                                                     final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("address", params);
        wrappedParams.put("carrier", carrier);

        AddressVerifyResponse response;
        response = request(RequestMethod.POST, String.format("%s/create_and_verify", classURL(Address.class)),
                wrappedParams, AddressVerifyResponse.class, apiKey);

        if (response.getMessage() != null) {
            response.getAddress().setMessage(response.getMessage());
        }
        return response.getAddress();
    }

    /**
     * Verify this Address object.
     *
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public Address verify() throws EasyPostException {
        return this.verify(null);
    }

    /**
     * Verify this Address object.
     *
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public Address verify(final String apiKey) throws EasyPostException {
        AddressVerifyResponse response;
        response =
                request(RequestMethod.GET, String.format("%s/verify", instanceURL(Address.class, this.getId())), null,
                        AddressVerifyResponse.class, apiKey);

        if (response.getMessage() != null) {
            response.getAddress().setMessage(response.getMessage());
        }
        return response.getAddress();
    }

    /**
     * Verify this Address object with a specific carrier.
     *
     * @param carrier Carrier to verify address with.
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public Address verifyWithCarrier(final String carrier) throws EasyPostException {
        return this.verifyWithCarrier(carrier, null);
    }

    /**
     * Verify this Address object with a specific carrier.
     *
     * @param carrier Carrier to verify address with.
     * @param apiKey  API key to use in request (ovverides default API key).
     * @return Address object.
     * @throws EasyPostException when the request fails.
     */
    public Address verifyWithCarrier(final String carrier, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("carrier", carrier);

        AddressVerifyResponse response;
        response =
                request(RequestMethod.GET, String.format("%s/verify", instanceURL(Address.class, this.getId())), null,
                        AddressVerifyResponse.class, apiKey);

        if (response.getMessage() != null) {
            response.getAddress().setMessage(response.getMessage());
        }
        return response.getAddress();
    }
}
