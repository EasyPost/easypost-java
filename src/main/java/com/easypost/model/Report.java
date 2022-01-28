package com.easypost.model;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Report extends EasyPostResource {
    private String id;
    private Date startDate;
    private Date endDate;
    private String mode;
    private String status;
    private Boolean includeChildren;
    private String url;
    private Date urlExpiresAt;

    /**
     * Get the ID of this Report.
     *
     * @return the ID of this Report.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of this Report.
     *
     * @param id the ID of this Report.
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the start date of this Report.
     *
     * @return the start date of this Report.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Set the start date of this Report.
     *
     * @param startDate the start date of this Report.
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Get the end date of this Report.
     *
     * @return the end date of this Report.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Set the end date of this Report.
     *
     * @param endDate the end date of this Report.
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Get the mode of this Report.
     *
     * @return the mode of this Report
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set the mode of this Report.
     *
     * @param mode the mode of this Report.
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Get the status of this Report.
     *
     * @return the status of this Report.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status of this Report.
     *
     * @param status the status of this Report.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Get whether this Report includes children.
     *
     * @return whether this Report includes children.
     */
    public Boolean getIncludeChildren() {
        return includeChildren;
    }

    /**
     * Set whether this Report includes children.
     *
     * @param includeChildren whether this Report includes children.
     */
    public void setIncludeChildren(final Boolean includeChildren) {
        this.includeChildren = includeChildren;
    }

    /**
     * Get the URL of this Report.
     *
     * @return the URL of this Report.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the URL of this Report.
     *
     * @param url the URL of this Report.
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Get when the URL for this Report expires.
     *
     * @return when the URL for this Report expires.
     */
    public Date getUrlExpiresAt() {
        return urlExpiresAt;
    }

    /**
     * Set when the URL for this Report expires.
     *
     * @param urlExpiresAt when the URL for this Report expires.
     */
    public void setUrlExpiresAt(final Date urlExpiresAt) {
        this.urlExpiresAt = urlExpiresAt;
    }

    /**
     * Create a Report from a map of parameters.
     *
     * @param params a map of parameters.
     * @return Report object.
     * @throws EasyPostException when the request fails.
     */
    public static Report create(final Map<String, Object> params) throws EasyPostException {
        return create(params, null);
    }

    /**
     * Create a Report from a map of parameters.
     *
     * @param params a map of parameters.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Report object.
     * @throws EasyPostException when the request fails.
     */
    public static Report create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("report", params);
        wrappedParams.put("start_date", params.get("start_date"));
        wrappedParams.put("end_date", params.get("end_date"));

        String type = (String) params.get("type");
        return request(RequestMethod.POST, reportURL(type), wrappedParams, Report.class, apiKey);
    }

    /**
     * Retrieve a Report from the API.
     *
     * @param id the ID of the Report to retrieve.
     * @return Report object.
     * @throws EasyPostException when the request fails.
     */
    public static Report retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    /**
     * Retrieve a Report from the API.
     *
     * @param id     the ID of the Report to retrieve.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return Report object.
     * @throws EasyPostException when the request fails.
     */
    public static Report retrieve(final String id, final String apiKey) throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Report.class, id), null, Report.class, apiKey);
    }

    /**
     * Get a list of Reports from the API.
     *
     * @param params a map of parameters.
     * @return ReportCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static ReportCollection all(final Map<String, Object> params) throws EasyPostException {
        return all(params, null);
    }

    /**
     * Get a list of Reports from the API.
     *
     * @param params a map of parameters.
     * @param apiKey API key to use in request (ovverides default API key).
     * @return ReportCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static ReportCollection all(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        String type = (String) params.get("type");
        return request(RequestMethod.GET, reportURL(type), params, ReportCollection.class, apiKey);
    }

    /**
     * Generate a report URL.
     *
     * @param type the type of report to generate.
     * @return the URL to generate the report.
     * @throws EasyPostException when the request fails.
     */
    protected static String reportURL(final String type) throws EasyPostException {
        try {
            String urlType = URLEncoder.encode(type, "UTF-8").toLowerCase();
            return String.format("%s/reports/%s/", EasyPost.API_BASE, urlType);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new EasyPostException("Undetermined Report Type");
        }
    }
}
