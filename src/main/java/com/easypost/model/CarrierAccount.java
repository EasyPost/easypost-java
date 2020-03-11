package com.easypost.model;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class CarrierAccount extends EasyPostResource {
    public String id;
    String readable;
    String description;
		String reference;
		Map<String, Object> credentials;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getReadable() { return readable; }
    public void setReadable(String readable) { this.readable = readable; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

		public String getReference() {
		return reference;
	}
		public void setReference(String reference) {
		this.reference = reference;
	}

		public Map<String, Object> getCredentials() {
		return credentials;
	}
		public void setCredentials(Map<String, Object> credentials) {
		this.credentials = credentials;
	}

		// update
		public CarrierAccount update(Map<String, Object> params) throws EasyPostException {
			return this.update(params, null);
		}
		public CarrierAccount update(Map<String, Object> params, String apiKey) throws EasyPostException {
			Map<String, Object> wrappedParams = new HashMap<String, Object>();
			wrappedParams.put("carrier_account", params);

			CarrierAccount response = request(RequestMethod.PUT, instanceURL(CarrierAccount.class, this.getId()), wrappedParams, CarrierAccount.class, apiKey);

			this.merge(this, response);
			return this;
		}

		// delete
		public void delete() throws EasyPostException {
			this.delete(null);
		}
		public void delete(String apiKey) throws EasyPostException {
			request(RequestMethod.DELETE, instanceURL(CarrierAccount.class, this.getId()), null, CarrierAccount.class, apiKey);
		}

		// create
		public static CarrierAccount create(Map<String, Object> params) throws EasyPostException {
			return create(params, null);
		}
		public static CarrierAccount create(Map<String, Object> params, String apiKey) throws EasyPostException {
			Map<String, Object> wrappedParams = new HashMap<String, Object>();
			wrappedParams.put("carrier_account", params);

			return request(RequestMethod.POST, classURL(CarrierAccount.class), wrappedParams, CarrierAccount.class, apiKey);
		}

		// createFedEx
		public static CarrierAccount createFedEx(Map<String, Object> params) throws EasyPostException {
			return createFedEx(params, null);
		}
		public static CarrierAccount createFedEx(Map<String, Object> params, String apiKey) throws EasyPostException {
			Map<String, Object> wrappedParams = new HashMap<String, Object>();
			// the description is required by the endpoint; manually supply it here to ensure that the call goes through
			params.put("description", "FedEx Account");
			// wrap the parameters, as required by the endpoint
			wrappedParams.put("fedex_registration", params);
			
			return request(RequestMethod.POST, String.format("%s/%s", EasyPost.API_BASE, "fedex_registrations"), wrappedParams, CarrierAccount.class, apiKey);
		}

		// retrieve
		public static CarrierAccount retrieve(String id) throws EasyPostException {
			return retrieve(id, null);
		}
		public static CarrierAccount retrieve(String id, String apiKey) throws EasyPostException {
			return request(RequestMethod.GET, instanceURL(CarrierAccount.class, id), null, CarrierAccount.class, apiKey);
		}

		// all
		public static List<CarrierAccount> all(Map<String, Object> params) throws EasyPostException {
			return all(params, null);
		}
		public static List<CarrierAccount> all(Map<String, Object> params, String apiKey) throws EasyPostException {
			CarrierAccount[] response = request(RequestMethod.GET, classURL(CarrierAccount.class), params, CarrierAccount[].class, apiKey);
			return Arrays.asList(response);
		}



	}
