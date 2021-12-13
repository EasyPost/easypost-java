/**
 * Report.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.net.EasyPostResource;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Report extends EasyPostResource {
    public String id;
    private Date startDate;
    private Date endDate;
    private String mode;
    private String status;
    private Boolean includeChildren;
    private String url;
    private Date urlExpiresAt;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(final String mode) {
        this.mode = mode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Boolean getIncludeChildren() {
        return includeChildren;
    }

    public void setIncludeChildren(final Boolean includeChildren) {
        this.includeChildren = includeChildren;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Date getUrlExpiresAt() {
        return urlExpiresAt;
    }

    public void setUrlExpiresAt(final Date urlExpiresAt) {
        this.urlExpiresAt = urlExpiresAt;
    }

    // create
    public static Report create(final Map<String, Object> params)
            throws EasyPostException {
        return create(params, null);
    }

    public static Report create(final Map<String, Object> params,
                                final String apiKey) throws EasyPostException {
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put("report", params);
        wrappedParams.put("start_date", params.get("start_date"));
        wrappedParams.put("end_date", params.get("end_date"));

        String type = (String) params.get("type");
        return request(RequestMethod.POST, reportURL(type), wrappedParams,
                Report.class, apiKey);
    }

    // retrieve
    public static Report retrieve(final String id) throws EasyPostException {
        return retrieve(id, null);
    }

    public static Report retrieve(final String id, final String apiKey)
            throws EasyPostException {
        return request(RequestMethod.GET, instanceURL(Report.class, id), null,
                Report.class, apiKey);
    }

    // all
    public static ReportCollection all(final Map<String, Object> params)
            throws EasyPostException {
        return all(params, null);
    }

    public static ReportCollection all(final Map<String, Object> params,
                                       final String apiKey)
            throws EasyPostException {
        String type = (String) params.get("type");
        return request(RequestMethod.GET, reportURL(type), params,
                ReportCollection.class, apiKey);
    }

    // generate report URL pattern
    protected static String reportURL(final String type)
            throws EasyPostException {
        try {
            String urlType = URLEncoder.encode(type, "UTF-8").toLowerCase();
            return String.format("%s/reports/%s/", EasyPost.API_BASE, urlType);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new EasyPostException("Undetermined Report Type");
        }
    }
}
