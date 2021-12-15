/**
 * AddressDetail.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

public final class AddressDetail {
    private Float latitude;
    private Float longitude;
    private String timeZone;

    /**
     * Get latitude of the address.
     *
     * @return latitude of the address.
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * Set latitude of the address.
     *
     * @param latitude latitude of the address.
     */
    public void setLatitude(final Float latitude) {
        this.latitude = latitude;
    }

    /**
     * Get longitude of the address.
     *
     * @return longitude of the address.
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * Set longitude of the address.
     *
     * @param longitude longitude of the address.
     */
    public void setLongitude(final Float longitude) {
        this.longitude = longitude;
    }

    /**
     * Get time zone of the address.
     *
     * @return time zone of the address.
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Set time zone of the address.
     *
     * @param timeZone time zone of the address.
     */
    public void setCode(final String timeZone) {
        this.timeZone = timeZone;
    }
}
