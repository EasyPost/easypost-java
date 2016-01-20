package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Address extends EasyPostResource {
  public String id;
  String mode;
  String name;
  String company;
  String street1;
  String street2;
  String zip;
  String city;
  String state;
  String country;
  String phone;
  String email;
  String message;
  String carrierFacility;
  String federalTaxId;
  Boolean residential;
  Map<String,AddressVerification> verifications;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getMode() {
    return mode;
  }
  public void setMode(String mode) {
    this.mode = mode;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getCompany() {
    return company;
  }
  public void setCompany(String company) {
    this.company = company;
  }

  public String getStreet1() {
    return street1;
  }
  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public String getStreet2() {
    return street2;
  }
  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public String getZip() {
    return zip;
  }
  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }

  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

  public String getCarrierFacility() {
    return carrierFacility;
  }
  public void setCarrierFacility(String carrierFacility) {
    this.carrierFacility = carrierFacility;
  }

  public String getFederalTaxId() {
    return federalTaxId;
  }
  public void setFederalTaxId(String federalTaxId) {
    this.federalTaxId = federalTaxId;
  }

  public Boolean getResidential() {
    return residential;
  }
  public void setResidential(Boolean residential) {
    this.residential = residential;
  }

  public Map<String,AddressVerification> getVerifications() { return verifications;  }
  public void setVerifications(Map<String,AddressVerification> verifications) { this.verifications = verifications; }

  // create
  public static Address create(Map<String, Object> params) throws EasyPostException {
    return create(params, null);
  }
  public static Address create(Map<String, Object> params, String apiKey) throws EasyPostException {
    String url = classURL(Address.class);

    List<String> verifyList = (List<String>) params.remove("verify");
    List<String> verifyStrictList = (List<String>) params.remove("verify_strict");

    if ((verifyList != null && verifyList.size() >= 1) || (verifyStrictList != null && verifyStrictList.size() >= 1)) {
      url += "?";

      if (verifyList != null && verifyList.size() >= 1) {
        for (String verification : verifyList) {
          url += "verify[]=" + verification + "&";
        }
      }

      if (verifyStrictList != null && verifyStrictList.size() >= 1) {
        for (String strictVerification : verifyStrictList) {
          url += "verify_strict[]=" + strictVerification + "&";
        }
      }
    }

    Map<String, Object> wrappedParams = new HashMap<String, Object>();
    wrappedParams.put("address", params);

    return request(RequestMethod.POST, url, wrappedParams, Address.class, apiKey);
  }

  // retrieve
  public static Address retrieve(String id) throws EasyPostException {
    return retrieve(id, null);
  }
  public static Address retrieve(String id, String apiKey) throws EasyPostException {
    return request(RequestMethod.GET, instanceURL(Address.class, id), null, Address.class, apiKey);
  }

  // all
  public static AddressCollection all(Map<String, Object> params) throws EasyPostException {
    return all(params, null);
  }
  public static AddressCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
    return request(RequestMethod.GET, classURL(Address.class), params, AddressCollection.class, apiKey);
  }

  // createAndVerify
  public static Address createAndVerify(Map<String, Object> params) throws EasyPostException {
    return createAndVerify(params, null);
  }
  public static Address createAndVerify(Map<String, Object> params, String apiKey) throws EasyPostException {
    Map<String, Object> wrappedParams = new HashMap<String, Object>();
    wrappedParams.put("address", params);

    AddressVerifyResponse response;
    response = request(RequestMethod.POST, String.format("%s/create_and_verify", classURL(Address.class)), wrappedParams, AddressVerifyResponse.class, apiKey);

    if (response.message != null) {
      response.address.message = response.message;
    }
    return response.address;
  }

  // createAndVerifyWithCarrier
  public static Address createAndVerifyWithCarrier(Map<String, Object> params, String carrier) throws EasyPostException {
    return createAndVerifyWithCarrier(params, carrier, null);
  }
  public static Address createAndVerifyWithCarrier(Map<String, Object> params, String carrier, String apiKey) throws EasyPostException {
    Map<String, Object> wrappedParams = new HashMap<String, Object>();
    wrappedParams.put("address", params);
    wrappedParams.put("carrier", carrier);

    AddressVerifyResponse response;
    response = request(RequestMethod.POST, String.format("%s/create_and_verify", classURL(Address.class)), wrappedParams, AddressVerifyResponse.class, apiKey);

    if (response.message != null) {
      response.address.message = response.message;
    }
    return response.address;
  }

  // verify
  public Address verify() throws EasyPostException {
    return this.verify(null);
  }
  public Address verify(String apiKey) throws EasyPostException {
    AddressVerifyResponse response;
    response = request(RequestMethod.GET, String.format("%s/verify", instanceURL(Address.class, this.getId())), null, AddressVerifyResponse.class, apiKey);

    if (response.message != null) {
      response.address.message = response.message;
    }
    return response.address;
  }

  // verifyWithCarrier
  public Address verifyWithCarrier(String carrier) throws EasyPostException {
    return this.verifyWithCarrier(carrier, null);
  }
  public Address verifyWithCarrier(String carrier, String apiKey) throws EasyPostException {
    Map<String, Object> wrappedParams = new HashMap<String, Object>();
    wrappedParams.put("carrier", carrier);

    AddressVerifyResponse response;
    response = request(RequestMethod.GET, String.format("%s/verify", instanceURL(Address.class, this.getId())), null, AddressVerifyResponse.class, apiKey);

    if (response.message != null) {
      response.address.message = response.message;
    }
    return response.address;
  }

}
