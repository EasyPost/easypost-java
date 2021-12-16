package com.easypost.model;

import com.easypost.model.enums.FormType;
import com.easypost.model.enums.Mode;

public class Form {
  public String id;
  Mode mode;
  FormType formType;
  String formUrl;
  Boolean submittedElectronically;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public Mode getMode() {
    return mode;
  }
  public void setMode(Mode mode) {
    this.mode = mode;
  }

  public FormType getFormType() {
    return formType;
  }
  public void setFormType(FormType formType) {
    this.formType = formType;
  }

  public String getFormUrl() {
    return formUrl;
  }
  public void setFormUrl(String formUrl) {
    this.formUrl = formUrl;
  }

  public Boolean getSubmittedElectronically() {
    return submittedElectronically;
  }
  public void setSubmittedElectronically(Boolean submittedElectronically) {
    this.submittedElectronically = submittedElectronically;
  }
}
