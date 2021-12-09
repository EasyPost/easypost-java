package com.easypost.model;


import com.easypost.net.EasyPostResource;

public class TaxIdentifier extends EasyPostResource {

    String entity;
    String taxID;
    String taxIDType;
    String issuingCountry;

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return entity;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxIDType(String taxIDType) {
        this.taxIDType = taxIDType;
    }

    public String getTaxIDType() {
        return taxIDType;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

}
