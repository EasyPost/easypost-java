package com.easypost.model;

import java.util.Date;

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
}
