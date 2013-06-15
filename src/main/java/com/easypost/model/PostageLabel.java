package com.easypost.model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

public class PostageLabel extends EasyPostResource {
	public String id;
	int dateAdvance;
    String integratedForm;
    int labelResolution;
    String labelSize;
    String labelType;
    String labelUrl;
    String labelFileType;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getDateAdvance() {
		return dateAdvance;
	}
	public void setDateAdvance(int dateAdvance) {
		this.dateAdvance = dateAdvance;
	}

	public String getIntegratedForm() {
		return integratedForm;
	}
	public void setIntegratedForm(String integratedForm) {
		this.integratedForm = integratedForm;
	}

	public int getLabelResolution() {
		return labelResolution;
	}
	public void setLabelResolution(int labelResolution) {
		this.labelResolution = labelResolution;
	}

	public String getLabelSize() {
		return labelSize;
	}
	public void setLabelSize(String labelSize) {
		this.labelSize = labelSize;
	}

	public String getLabelType() {
		return labelType;
	}
	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}

	public String getLabelUrl() {
		return labelUrl;
	}
	public void setLabelUrl(String labelUrl) {
		this.labelUrl = labelUrl;
	}

	public String getLabelFileType() {
		return labelFileType;
	}
	public void setLabelFileType(String labelFileType) {
		this.labelFileType = labelFileType;
	}


	// create
	public static PostageLabel create(Map<String, Object> params) throws EasyPostException {
		return create(params, null);
	}
	public static PostageLabel create(Map<String, Object> params, String apiKey) throws EasyPostException {
		Map<String, Object> wrappedParams = new HashMap<String, Object>();
		wrappedParams.put("parcel", params);
		
		return request(RequestMethod.POST, classURL(PostageLabel.class), wrappedParams, PostageLabel.class, apiKey);
	}

	// retrieve
	public static PostageLabel retrieve(String id) throws EasyPostException {
		return retrieve(id, null);
	}
	public static PostageLabel retrieve(String id, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, instanceURL(PostageLabel.class, id), null, PostageLabel.class, apiKey);
	}

	// all
	public static PostageLabelCollection all(Map<String, Object> params) throws EasyPostException {
		return all(params, null);
	}
	public static PostageLabelCollection all(Map<String, Object> params, String apiKey) throws EasyPostException {
		return request(RequestMethod.GET, classURL(PostageLabel.class), params, PostageLabelCollection.class, apiKey);
	}

}