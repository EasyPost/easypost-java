package com.easypost.model;

import java.util.Map;

public final class CarrierType extends EasyPostResource {
    private String type;
    private String readable;
    private String logo;
    private Map<String, Object> fields;

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
}
