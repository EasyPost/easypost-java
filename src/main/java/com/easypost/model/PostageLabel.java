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
	private void setId(String id) {
		this.id = id;
	}

	public int getDateAdvance() {
		return dateAdvance;
	}
	private void setDateAdvance(int dateAdvance) {
		this.dateAdvance = dateAdvance;
	}

	public String getIntegratedForm() {
		return integratedForm;
	}
	private void setIntegratedForm(String integratedForm) {
		this.integratedForm = integratedForm;
	}

	public int getLabelResolution() {
		return labelResolution;
	}
	private void setLabelResolution(int labelResolution) {
		this.labelResolution = labelResolution;
	}

	public String getLabelSize() {
		return labelSize;
	}
	private void setLabelSize(String labelSize) {
		this.labelSize = labelSize;
	}

	public String getLabelType() {
		return labelType;
	}
	private void setLabelType(String labelType) {
		this.labelType = labelType;
	}

	public String getLabelUrl() {
		return labelUrl;
	}
	private void setLabelUrl(String labelUrl) {
		this.labelUrl = labelUrl;
	}

	public String getLabelFileType() {
		return labelFileType;
	}
	private void setLabelFileType(String labelFileType) {
		this.labelFileType = labelFileType;
	}

	public String getLabelPdfSize() {
		return labelPdfSize;
	}
	private void setLabelPdfSize(String labelPdfSize) {
		this.labelPdfSize = labelPdfSize;
	}

	public String getPdfLabelType() {
		return labelPdfType;
	}
	private void setLabelPdfType(String labelPdfType) {
		this.labelPdfType = labelPdfType;
	}

	public String getLabelPdfUrl() {
		return labelPdfUrl;
	}
	private void setLabelPdfUrl(String labelPdfUrl) {
		this.labelPdfUrl = labelPdfUrl;
	}

	public String getLabelPdfFileType() {
		return labelPdfFileType;
	}
	private void setLabelPdfFileType(String labelPdfFileType) {
		this.labelPdfFileType = labelPdfFileType;
	}

	public String getLabelEpl2Size() {
		return labelEpl2Size;
	}
	private void setLabelEpl2Size(String labelEpl2Size) {
		this.labelEpl2Size = labelEpl2Size;
	}

	public String getEpl2LabelType() {
		return labelEpl2Type;
	}
	private void setLabelEpl2Type(String labelEpl2Type) {
		this.labelEpl2Type = labelEpl2Type;
	}

	public String getLabelEpl2Url() {
		return labelEpl2Url;
	}
	private void setLabelEpl2Url(String labelEpl2Url) {
		this.labelEpl2Url = labelEpl2Url;
	}

	public String getLabelEpl2FileType() {
		return labelEpl2FileType;
	}
	private void setLabelEpl2FileType(String labelEpl2FileType) {
		this.labelEpl2FileType = labelEpl2FileType;
	}

	public String getLabelZplSize() {
		return labelZplSize;
	}
	private void setLabelZplSize(String labelZplSize) {
		this.labelZplSize = labelZplSize;
	}

	public String getZplLabelType() {
		return labelZplType;
	}
	private void setLabelZplType(String labelZplType) {
		this.labelZplType = labelZplType;
	}

	public String getLabelZplUrl() {
		return labelZplUrl;
	}
	private void setLabelZplUrl(String labelZplUrl) {
		this.labelZplUrl = labelZplUrl;
	}

	public String getLabelZplFileType() {
		return labelZplFileType;
	}
	private void setLabelZplFileType(String labelZplFileType) {
		this.labelZplFileType = labelZplFileType;
	}
}
