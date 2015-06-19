package com.easypost.model;

public class Form {
  public String id;
  String mode;
  String formType;
  String formUrl;

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

  public String getFormType() {
    return formType;
  }
  public void setFormType(String formType) {
    this.formType = formType;
  }

  public String getFormUrl() {
    return formUrl;
  }
  public void setFormUrl(String formUrl) {
    this.formUrl = formUrl;
  }
}
