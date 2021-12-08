package com.easypost.model;


import com.easypost.net.EasyPostResource;

public class TaxIdentifier extends EasyPostResource {

    public String entity;
    public String taxId;
    public String taxIdType;
    public String issuingCountry;

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return entity;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxIdType(String taxIdType) {
        this.taxIdType = taxIdType;
    }

    public String getTaxIdType() {
        return taxIdType;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

}
