/**
 * ShipmentMessage.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

public final class ShipmentMessage {
    private String carrier;
    private String type;
    private Object message;

    /**
     * Get the carrier associated with this message.
     *
     * @return the carrier associated with this message.
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Set the carrier associated with this message.
     *
     * @param carrier the carrier associated with this message.
     */
    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    /**
     * Get the type of this message.
     *
     * @return the type of this message.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of this message.
     *
     * @param type the type of this message.
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Get the contents of this message.
     *
     * @return the contents of this message.
     */
    public Object getMessage() {
        return message;
    }

    /**
     * Set the contents of this message.
     *
     * @param message the contents of this message.
     */
    public void setMessage(final Object message) {
        this.message = message;
    }
}
