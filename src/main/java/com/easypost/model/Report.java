package com.easypost.model;

import com.easypost.EasyPost;
import com.easypost.exception.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.InvalidObjectError;
import com.easypost.exception.General.InvalidParameterError;
import com.easypost.net.EasyPostResource;
import com.easypost.net.Requestor;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Report extends EasyPostResource {
    private Date startDate;
    private Date endDate;
    private String status;
    private Boolean includeChildren;
    private String url;
    private Date urlExpiresAt;

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
     * @param apiKey API key to use in request (overrides default API key).
     * @return Report object.
     * @throws EasyPostException when the request fails.
     */
    public static Report create(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        if (params.containsKey("type")) {
            HashMap<String, Object> paramsWithoutType = new HashMap<>(params);
            paramsWithoutType.remove("type");
            return Requestor.request(RequestMethod.POST, 
                reportURL((String) params.get("type")), paramsWithoutType, Report.class, apiKey);
        } else {
            throw new InvalidObjectError(String.format(Constants.MISSING_REQUIRED_PARAMETER, "type"));
        }
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
            throw new InvalidParameterError(String.format(Constants.ENCODED_ERROR, "report type"), e);
        }
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
     * @param apiKey API key to use in request (overrides default API key).
     * @return Report object.
     * @throws EasyPostException when the request fails.
     */
    public static Report retrieve(final String id, final String apiKey) throws EasyPostException {
        return Requestor.request(RequestMethod.GET, instanceURL(Report.class, id), null, Report.class, apiKey);
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
     * @param apiKey API key to use in request (overrides default API key).
     * @return ReportCollection object.
     * @throws EasyPostException when the request fails.
     */
    public static ReportCollection all(final Map<String, Object> params, final String apiKey) throws EasyPostException {
        String type = (String) params.get("type");
        return Requestor.request(RequestMethod.GET, reportURL(type), params, ReportCollection.class, apiKey);
    }
}
