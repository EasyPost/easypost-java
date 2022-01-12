/**
 * TrackingDetail.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import java.util.Date;

public final class TrackingDetail {
    private String status;
    private String message;
    private Date datetime;
    private TrackingLocation trackingLocation;
    private String statusDetail;

    /**
     * Get the status of the tracking detail.
     *
     * @return the status of the tracking detail.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status of the tracking detail.
     *
     * @param status the status of the tracking detail.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Get the message of the tracking detail.
     *
     * @return the message of the tracking detail.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message of the tracking detail.
     *
     * @param message the message of the tracking detail.
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Get the datetime of the tracking detail.
     *
     * @return the datetime of the tracking detail.
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * Set the datetime of the tracking detail.
     *
     * @param datetime the datetime of the tracking detail.
     */
    public void setDatetime(final Date datetime) {
        this.datetime = datetime;
    }

    /**
     * Get the tracking location of the tracking detail.
     *
     * @return TrackingLocation object.
     */
    public TrackingLocation getTrackingLocation() {
        return trackingLocation;
    }

    /**
     * Set the tracking location of the tracking detail.
     *
     * @param location TrackingLocation object.
     */
    public void setTrackingLocation(final TrackingLocation location) {
        this.trackingLocation = location;
    }

    /**
     * Get the status detail of the tracking detail.
     *
     * @return the status detail of the tracking detail.
     */
    public String getStatusDetail() {
        return statusDetail;
    }

    /**
     * Set the status detail of the tracking detail.
     *
     * @param statusDetail the status detail of the tracking detail.
     */
    public void setStatusDetail(final String statusDetail) {
        this.statusDetail = statusDetail;
    }

}
