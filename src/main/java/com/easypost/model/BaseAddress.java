package com.easypost.model;

import com.easypost.net.EasyPostResource;

public class BaseAddress extends EasyPostResource {
    private String name;
    private String company;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String email;

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
     * Get company of Address.
     *
     * @return Address company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set company of Address.
     *
     * @param company Address company
     */
    public void setCompany(final String company) {
        this.company = company;
    }

    /**
     * Get country of Address.
     *
     * @return Address country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set country of Address.
     *
     * @param country Address country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Get email of Address.
     *
     * @return Address email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email of Address.
     *
     * @param email Address email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Get name of Address.
     *
     * @return Address name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of Address.
     *
     * @param name Address name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get phone number of Address.
     *
     * @return Address phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set phone number of Address.
     *
     * @param phone Address phone number
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * Get state of Address.
     *
     * @return Address state
     */
    public String getState() {
        return state;
    }

    /**
     * Set state of Address.
     *
     * @param state Address state
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * Get first line of Address street.
     *
     * @return first line of Address street
     */
    public String getStreet1() {
        return street1;
    }

    /**
     * Set first line of Address street.
     *
     * @param street1 first line of Address street
     */
    public void setStreet1(final String street1) {
        this.street1 = street1;
    }

    /**
     * Get second line of Address street.
     *
     * @return second line of Address street
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * Set second line of Address street.
     *
     * @param street2 second line of Address street
     */
    public void setStreet2(final String street2) {
        this.street2 = street2;
    }

    /**
     * Get zip code of Address.
     *
     * @return Address zip code
     */
    public String getZip() {
        return zip;
    }

    /**
     * Set zip code of Address.
     *
     * @param zip Address zip code
     */
    public void setZip(final String zip) {
        this.zip = zip;
    }
}
