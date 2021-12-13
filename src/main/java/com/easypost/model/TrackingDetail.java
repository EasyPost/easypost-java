/**
 * TrackingDetail.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
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

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(final Date datetime) {
        this.datetime = datetime;
    }

    public TrackingLocation getTrackingLocation() {
        return trackingLocation;
    }

    public void setTrackingLocation(final TrackingLocation location) {
        this.trackingLocation = location;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(final String statusDetail) {
        this.statusDetail = statusDetail;
    }

}
