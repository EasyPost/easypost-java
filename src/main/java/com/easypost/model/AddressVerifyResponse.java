/**
 * AddressVerifyResponse.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

public class AddressVerifyResponse {
    public Address address;
    public String message;

    /**
     * Get the Address object.
     * @return Address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the Address object.
     * @param address Address
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * Get the message.
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message.
     * @param message message
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
