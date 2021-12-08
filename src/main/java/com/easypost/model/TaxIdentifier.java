package com.easypost.model;


public class TaxIdentifier{
    
    public String entity;
    public String taxID;
    public String taxIDType;
    public String issuingCountry;

    public void setEntiy(String entity){
        this.entity = entity;
    }
    public String getEntity(){
        return entity;
    }

    public void setTaxID(String taxID){
        this.taxID = taxID;
    }
    public String getTaxID(){
        return taxID;
    }

    public void setTaxIDType(String taxIDType){
        this.taxIDType = taxIDType;
    }
    public String getTaxIDType(){
        return taxIDType;
    }

    public void setIssuingCountry(String issuingCountry){
        this.issuingCountry = issuingCountry;
    }
    public String getIssuingCountry(){
        return issuingCountry;
    }
    
}
