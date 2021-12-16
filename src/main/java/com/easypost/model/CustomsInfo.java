package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.CustomsContentsType;
import com.easypost.model.enums.CustomsNonDeliveryOption;
import com.easypost.model.enums.CustomsRestrictionType;
import com.easypost.net.EasyPostResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomsInfo extends EasyPostResource {
	public String id;
	CustomsContentsType contentsType;
	String contentsExplanation;
	boolean customsCertify;
	String customsSigner;
	CustomsNonDeliveryOption nonDeliveryOption;
	CustomsRestrictionType restrictionType;
	String restrictionComments;
	List<CustomsItem> customsItems;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public CustomsContentsType getContentsType() {
		return contentsType;
	}
	public void setContentsType(CustomsContentsType contentsType) {
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

	public CustomsNonDeliveryOption getNonDeliveryOption() {
		return nonDeliveryOption;
	}
	public void setNonDeliveryOption(CustomsNonDeliveryOption nonDeliveryOption) {
		this.nonDeliveryOption = nonDeliveryOption;
	}

	public CustomsRestrictionType getRestrictionType() {
		return restrictionType;
	}
	public void setRestrictionType(CustomsRestrictionType restrictionType) {
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
