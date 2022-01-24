/**
 * CarrierAccount.java
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
     * @return type of a carrier
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type type of a carrier
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     *
     * @return return readable from a carrier
     */
    public String getReadable() {
        return readable;
    }

    /**
     *
     * @param readable readable for a carrier
     */
    public void setReadable(final String readable) {
        this.readable = readable;
    }

    /**
     *
     * @return logo of a carrier
     */
    public String getLogo() {
        return logo;
    }

    /**
     *
     * @param logo logo of a carrier
     */
    public void setLogo(final String logo) {
        this.logo = logo;
    }

    /**
     *
     * @return fields from a carrier
     */
    public Map<String, Object> getFields() {
        return fields;
    }

    /**
     *
     * @param fields fields for a carrier
     */
    public void setFields(final Map<String, Object> fields) {
        this.fields = fields;
    }

    /**
     *
     * @return list of carrier type that is available to the account
     * @throws EasyPostException
     */
    public static List<CarrierType> all() throws EasyPostException {
        return all(null);
    }

    /**
     *
     * @param apikey the API key from the user input
     * @return list of carrier type that is available to the account of the given API key
     * @throws EasyPostException
     */
    public static List<CarrierType> all(final String apikey) throws EasyPostException {
        CarrierType[] ret = request(RequestMethod.GET, classURL(CarrierType.class), null, CarrierType[].class, apikey);
        return Arrays.asList(ret);
    }
}
