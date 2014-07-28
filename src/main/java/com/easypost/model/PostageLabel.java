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
  String labelPdfSize;
  String labelPdfType;
  String labelPdfUrl;
  String labelPdfFileType;
  String labelEpl2Size;
  String labelEpl2Type;
  String labelEpl2Url;
  String labelEpl2FileType;
  String labelZplSize;
  String labelZplType;
  String labelZplUrl;
  String labelZplFileType;

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

	public String getLabelPdfSize() {
		return labelPdfSize;
	}
	public void setLabelPdfSize(String labelPdfSize) {
		this.labelPdfSize = labelPdfSize;
	}

	public String getPdfLabelType() {
		return labelPdfType;
	}
	public void setLabelPdfType(String labelPdfType) {
		this.labelPdfType = labelPdfType;
	}

	public String getLabelPdfUrl() {
		return labelPdfUrl;
	}
	public void setLabelPdfUrl(String labelPdfUrl) {
		this.labelPdfUrl = labelPdfUrl;
	}

	public String getLabelPdfFileType() {
		return labelPdfFileType;
	}
	public void setLabelPdfFileType(String labelPdfFileType) {
		this.labelPdfFileType = labelPdfFileType;
	}

	public String getLabelEpl2Size() {
		return labelEpl2Size;
	}
	public void setLabelEpl2Size(String labelEpl2Size) {
		this.labelEpl2Size = labelEpl2Size;
	}

	public String getEpl2LabelType() {
		return labelEpl2Type;
	}
	public void setLabelEpl2Type(String labelEpl2Type) {
		this.labelEpl2Type = labelEpl2Type;
	}

	public String getLabelEpl2Url() {
		return labelEpl2Url;
	}
	public void setLabelEpl2Url(String labelEpl2Url) {
		this.labelEpl2Url = labelEpl2Url;
	}

	public String getLabelEpl2FileType() {
		return labelEpl2FileType;
	}
	public void setLabelEpl2FileType(String labelEpl2FileType) {
		this.labelEpl2FileType = labelEpl2FileType;
	}

	public String getLabelZplSize() {
		return labelZplSize;
	}
	public void setLabelZplSize(String labelZplSize) {
		this.labelZplSize = labelZplSize;
	}

	public String getZplLabelType() {
		return labelZplType;
	}
	public void setLabelZplType(String labelZplType) {
		this.labelZplType = labelZplType;
	}

	public String getLabelZplUrl() {
		return labelZplUrl;
	}
	public void setLabelZplUrl(String labelZplUrl) {
		this.labelZplUrl = labelZplUrl;
	}

	public String getLabelZplFileType() {
		return labelZplFileType;
	}
	public void setLabelZplFileType(String labelZplFileType) {
		this.labelZplFileType = labelZplFileType;
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

}
