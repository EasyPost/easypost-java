/**
 * TaxIdentifier.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;


import com.easypost.net.EasyPostResource;

public final class TaxIdentifier extends EasyPostResource {

    private String entity;
    private String taxId;
    private String taxIdType;
    private String issuingCountry;

    /**
     * Get the entity associated with this TaxIdentifier.
     *
     * @return the entity associated with this TaxIdentifier.
     */
    public String getEntity() {
        return entity;
    }

    /**
     * Set the entity associated with this TaxIdentifier.
     *
     * @param entity the entity associated with this TaxIdentifier.
     */
    public void setEntity(final String entity) {
        this.entity = entity;
    }

    /**
     * Get the tax ID associated with this TaxIdentifier.
     *
     * @return the tax ID associated with this TaxIdentifier.
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * Set the tax ID associated with this TaxIdentifier.
     *
     * @param taxId the tax ID associated with this TaxIdentifier.
     */
    public void setTaxId(final String taxId) {
        this.taxId = taxId;
    }

    /**
     * Get the tax ID type associated with this TaxIdentifier.
     *
     * @return the tax ID type associated with this TaxIdentifier.
     */
    public String getTaxIdType() {
        return taxIdType;
    }

    /**
     * Set the tax ID type associated with this TaxIdentifier.
     *
     * @param taxIdType the tax ID type associated with this TaxIdentifier.
     */
    public void setTaxIdType(final String taxIdType) {
        this.taxIdType = taxIdType;
    }

    /**
     * Get the issuing country associated with this TaxIdentifier.
     *
     * @return the issuing country associated with this TaxIdentifier.
     */
    public String getIssuingCountry() {
        return issuingCountry;
    }

    /**
     * Set the issuing country associated with this TaxIdentifier.
     *
     * @param issuingCountry the issuing country associated with this TaxIdentifier.
     */
    public void setIssuingCountry(final String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }
}
