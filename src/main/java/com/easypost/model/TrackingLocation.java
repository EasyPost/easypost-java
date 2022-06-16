package com.easypost.model;

public final class TrackingLocation {
    private String city;
    private String state;
    private String country;
    private String zip;

    /**
     * Get the city of the tracking location.
     *
     * @return the city of the tracking location.
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the city of the tracking location.
     *
     * @param city the city of the tracking location.
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Get the country of the tracking location.
     *
     * @return the country of the tracking location.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the country of the tracking location.
     *
     * @param country the country of the tracking location.
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Get the state of the tracking location.
     *
     * @return the state of the tracking location.
     */
    public String getState() {
        return state;
    }

    /**
     * Set the state of the tracking location.
     *
     * @param state the state of the tracking location.
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * Get the zip code of the tracking location.
     *
     * @return the zip code of the tracking location.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Set the zip code of the tracking location.
     *
     * @param zip the zip code of the tracking location.
     */
    public void setZip(final String zip) {
        this.zip = zip;
    }
}
