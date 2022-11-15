package com.easypost.model;

public final class Address extends BaseAddress {
    private String message;
    private String carrierFacility;
    private String federalTaxId;
    private Boolean residential;
    private AddressVerifications verifications;

    /**
     * Get carrier facility for address.
     *
     * @return address carrier facility
     */
    public String getCarrierFacility() {
        return carrierFacility;
    }

    /**
     * Set carrier facility for address.
     *
     * @param carrierFacility address carrier facility
     */
    public void setCarrierFacility(final String carrierFacility) {
        this.carrierFacility = carrierFacility;
    }

    /**
     * Get federal tax id of address.
     *
     * @return address federal tax id
     */
    public String getFederalTaxId() {
        return federalTaxId;
    }

    /**
     * Set federal tax id of address.
     *
     * @param federalTaxId address federal tax id
     */
    public void setFederalTaxId(final String federalTaxId) {
        this.federalTaxId = federalTaxId;
    }

    /**
     * Get address message.
     *
     * @return address message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set address message.
     *
     * @param message address message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Get whether address is residential.
     *
     * @return whether address is residential
     */
    public Boolean getResidential() {
        return residential;
    }

    /**
     * Set whether address is residential.
     *
     * @param residential whether address is residential
     */
    public void setResidential(final Boolean residential) {
        this.residential = residential;
    }

    /**
     * Get verifications for address.
     *
     * @return address verifications
     */
    public AddressVerifications getVerifications() {
        return verifications;
    }

    /**
     * Set verifications for address.
     *
     * @param verifications address verifications
     */
    public void setVerifications(final AddressVerifications verifications) {
        this.verifications = verifications;
    }
}
