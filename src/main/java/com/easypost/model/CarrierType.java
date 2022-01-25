/**
 * CarrierType.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class CarrierType extends EasyPostResource {
    private String type;
    private String readable;
    private String logo;
    private Map<String, Object> fields;

    /**
     * Get the type of the carrier.
     *
     * @return type of a carrier.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of carrier.
     *
     * @param type type of a carrier.
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Get the readable name of the carrier.
     *
     * @return return readable name of the carrier.
     */
    public String getReadable() {
        return readable;
    }

    /**
     * Set the readable name of the carrier.
     *
     * @param readable readable name of the carrier.
     */
    public void setReadable(final String readable) {
        this.readable = readable;
    }

    /**
     * Get the logo of the carrier.
     *
     * @return logo of a carrier.
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Set the logo of carrier.
     *
     * @param logo logo of a carrier.
     */
    public void setLogo(final String logo) {
        this.logo = logo;
    }

    /**
     * Get the fields of the carrier.
     *
     * @return fields from a carrier.
     */
    public Map<String, Object> getFields() {
        return fields;
    }

    /**
     * Set the fields of carrier.
     *
     * @param fields fields for a carrier.
     */
    public void setFields(final Map<String, Object> fields) {
        this.fields = fields;
    }

    /**
     * Retrieve a list of available carriers for the account.
     *
     * @return list of carrier types that are available to the account.
     * @throws EasyPostException when the request fails.
     */
    public static List<CarrierType> all() throws EasyPostException {
        return all(null);
    }

    /**
     * Retrieve a list of available carriers for the given account.
     *
     * @param apikey the API key from the user input.
     * @return list of carrier types that are available for the given account.
     * @throws EasyPostException when the request fails.
     */
    public static List<CarrierType> all(final String apikey) throws EasyPostException {
        CarrierType[] ret = request(RequestMethod.GET, classURL(CarrierType.class), null, CarrierType[].class, apikey);
        return Arrays.asList(ret);
    }
}
