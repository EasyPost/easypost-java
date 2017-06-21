package com.easypost.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

//import com.google.gson.reflect.TypeToken;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class Rating extends EasyPostResource {
	List<CarrierAccount> carrierAccounts;
	List<Parcel> parcels;
	Address toAddress;
	Address fromAddress;
	Map<String, Object> options;
	String uspsZone;
	CustomsInfo customsInfo;

	public List<CarrierAccount> getCarrier_Accounts() {return carrierAccounts;}
	public void setCarrier_Accounts() {this.carrierAccounts = carrierAccounts;}

	public List<Parcel>  getParcels() {return parcels;}
	public void setParcels() {this.parcels = parcels;}

	public Address getToAddress() {return toAddress;}
	public void setToAddress() {this.toAddress = toAddress;}

	public Address getFromAddress() {return fromAddress;}
	public void setFromAddress() {this.fromAddress = fromAddress;}

	public Map<String, Object> getOptions() {return options;}
	public void setOptions() {this.options = options;}

	public String getUspsZone() {return uspsZone;}
	public void setUspsZone() {this.uspsZone = uspsZone;}

	public CustomsInfo getCustomsInfo() {return customsInfo;}
	public void setCustomsInfo(CustomsInfo customsInfo) {this.customsInfo = customsInfo;}

	// create
	public static Rating create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static Rating create(Map<String, Object> params, String apiKey) throws EasyPostException {
		System.out.println(Rating.class);
		return request(RequestMethod.POST, "https://api.easypost.com/rating/v1/rates", params, Rating.class, apiKey);
	}

}
