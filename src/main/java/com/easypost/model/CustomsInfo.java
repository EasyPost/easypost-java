package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class CustomsInfo extends EasyPostResource {
	public String id;
	String contentsType;
	String contentsExplanation;
	boolean customsCertify;
	String customsSigner;
	String nonDeliveryOption;
	String restrictionType;
	String restrictionComments;
	List<CustomsItem> customsItems;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getContentsType() {
		return contentsType;
	}
	public void setContentsType(String contentsType) {
		this.contentsType = contentsType;
	}

	public String getContentsExplanation() {
		return contentsExplanation;
	}
	public void setContentsExplanation(String contentsExplanation) {
		this.contentsExplanation = contentsExplanation;
	}

	public boolean getCustomsCertify() {
		return customsCertify;
	}
	public void setCustomsCertify(boolean customsCertify) {
		this.customsCertify = customsCertify;
	}

	public String getCustomsSigner() {
		return customsSigner;
	}
	public void setCustomsSigner(String customsSigner) {
		this.customsSigner = customsSigner;
	}

	public String getNonDeliveryOption() {
		return nonDeliveryOption;
	}
	public void setNonDeliveryOption(String nonDeliveryOption) {
		this.nonDeliveryOption = nonDeliveryOption;
	}

	public String getRestrictionType() {
		return restrictionType;
	}
	public void setRestrictionType(String restrictionType) {
		this.restrictionType = restrictionType;
	}

	public String getRestrictionComments() {
		return restrictionComments;
	}
	public void setRestrictionComments(String restrictionComments) {
		this.restrictionComments = restrictionComments;
	}

	public List<CustomsItem> getCustomsItems() {
		return customsItems;
	}
	public void setCustomsItems(List<CustomsItem> customsItems) {
		this.customsItems = customsItems;
	}


	// create
	public static CustomsInfo create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static CustomsInfo create(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("customs_info", params);

		return request(RequestMethod.POST, classURL(CustomsInfo.class), wrappedParams, CustomsInfo.class, apiKey);
	}

	// retrieve
	public static CustomsInfo retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}
	public static CustomsInfo retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(CustomsInfo.class, id), null, CustomsInfo.class, apiKey);
	}

}
