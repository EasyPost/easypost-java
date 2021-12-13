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

    public void setEntity(final String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return entity;
    }

    public void setTaxId(final String taxId) {
        this.taxId = taxId;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxIdType(final String taxIdType) {
        this.taxIdType = taxIdType;
    }

    public String getTaxIdType() {
        return taxIdType;
    }

    public void setIssuingCountry(final String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

}
