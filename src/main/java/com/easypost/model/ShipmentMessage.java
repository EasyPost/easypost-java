/**
 * ShipmentMessage.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

public final class ShipmentMessage {
    private String carrier;
    private String type;
    private Object message;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(final String carrier) {
        this.carrier = carrier;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(final Object message) {
        this.message = message;
    }
}
