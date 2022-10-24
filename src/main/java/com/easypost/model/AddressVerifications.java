package com.easypost.model;

public class AddressVerifications extends EasyPostResource {
    private AddressVerification zip4;
    private AddressVerification delivery;

    /**
     * Get the delivery of the AddressVerifications.
     *
     * @return Delivery of the AddressVerifications.
     */
    public AddressVerification getDelivery() {
        return delivery;
    }

    /**
     * Set the delivery of the AddressVerification.
     *
     * @param delivery Delivery of the AddressVerification.
     */
    public void setDelivery(final AddressVerification delivery) {
        this.delivery = delivery;
    }

    /**
     * Get the zip4 of the AddressVerification.
     *
     * @return Zip4 of the AddressVerification.
     */
    public AddressVerification getZip4() {
        return zip4;
    }

    /**
     * Set the zip4 of the AddressVerification.
     *
     * @param zip4 Zip4 of the AddressVerification.
     */
    public void setZip4(final AddressVerification zip4) {
        this.zip4 = zip4;
    }
}
