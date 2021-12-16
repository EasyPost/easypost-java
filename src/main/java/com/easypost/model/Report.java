package com.easypost.model;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.Mode;
import com.easypost.model.enums.ReportStatus;
import com.easypost.net.EasyPostResource;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Report extends EasyPostResource {
  public String id;
  Date startDate;
  Date endDate;
  Mode mode;
  ReportStatus status;
  Boolean includeChildren;
  String url;
  Date urlExpiresAt;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Mode getMode() {
    return mode;
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  public ReportStatus getStatus() {
    return status;
  }

  public void setStatus(ReportStatus status) {
    this.status = status;
  }

  public Boolean getIncludeChildren() {
    return includeChildren;
  }

  public void setIncludeChildren(Boolean includeChildren) {
    this.includeChildren = includeChildren;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Date getUrlExpiresAt() {
    return urlExpiresAt;
  }

  public void setUrlExpiresAt(Date urlExpiresAt) {
    this.urlExpiresAt = urlExpiresAt;
  }

  // create
  public static Report create(Map<String, Object> params) throws EasyPostException {
    return create(params, null);
  }

  public static Report create(Map<String, Object> params, String apiKey) throws EasyPostException {
    Map<String, Object> wrappedParams = new HashMap<String, Object>();
    wrappedParams.put("report", params);
    wrappedParams.put("start_date", params.get("start_date"));
    wrappedParams.put("end_date", params.get("end_date"));

    String type = (String) params.get("type");
    return request(RequestMethod.POST, reportURL(type), wrappedParams, Report.class, apiKey);
  }

  // retrieve
  public static Report retrieve(String id) throws EasyPostException {
    return retrieve(id, null);
  }

  public static Report retrieve(String id, String apiKey) throws EasyPostException {
    return request(RequestMethod.GET, instanceURL(Report.class, id), null, Report.class, apiKey);
  }

  // all
  public static ReportCollection all(Map<String, Object> params) throws EasyPostException {
    return all(params, null);
  }

  public static ReportCollection all(Map<String, Object> params, String apiKey)
      throws EasyPostException {
    String type = (String) params.get("type");
    return request(RequestMethod.GET, reportURL(type), params, ReportCollection.class, apiKey);
  }

  // generate report URL pattern
  protected static String reportURL(String type) throws EasyPostException {
    try {
      String urlType = URLEncoder.encode(type, "UTF-8").toLowerCase();
      return String.format("%s/reports/%s/", EasyPost.API_BASE, urlType);
    } catch (java.io.UnsupportedEncodingException e) {
      throw new EasyPostException("Undetermined Report Type");
    }
  }
}
