package com.easypost.model;

public class AddressVerifyResponse {
    private Address address;
    private String message;

    /**
     * Get the Address object.
     *
     * @return Address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the Address object.
     *
     * @param address Address
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * Get the message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message.
     *
     * @param message message
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
